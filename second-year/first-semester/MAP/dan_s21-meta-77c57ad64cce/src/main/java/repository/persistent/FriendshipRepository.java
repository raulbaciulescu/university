package repository.persistent;

import domain.exceptions.InvalidException;
import domain.model.Friendship;
import domain.model.Tuple;
import domain.model.User;
import domain.util.Serializer;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class FriendshipRepository extends FileRepository<Long, Friendship> {

    public FriendshipRepository(@NotNull String fileName,
                                @NotNull final FriendshipSerializer.Resource resource) {
        super(fileName, new FriendshipSerializer(resource));
    }

    public final static class FriendshipSerializer extends Serializer<Friendship> {

        @NotNull private final Resource resource;

        public FriendshipSerializer(@NotNull Resource resource) {
            this.resource = resource;
        }

        @NotNull
        @Override
        public Friendship fromString(@NotNull String line) throws SQLException {
            final String[] attributes = line.split(";");
            final long leftId = Long.parseLong(attributes[1]);
            final long rightId = Long.parseLong(attributes[2]);
            User left = null;
            User right = null;

            for (@NotNull final User user : this.resource.getUsers()) {
                if (user.getId() != null &&
                        (user.getId().equals(leftId) || user.getId().equals(rightId))) {
                    if (left == null) {
                        left = user;
                    } else {
                        right = user;
                        break;
                    }
                }
            }
            if (left == null || right == null) {
                throw new InvalidException("invalid users!");
            }

            final Friendship friendShip = new Friendship(
                    LocalDateTime.parse(attributes[0]),
                    new Tuple<>(left, right)
            );
            friendShip.setId(leftId + rightId);
            return friendShip;
        }

        @NotNull
        @Override
        public String toString(@NotNull Friendship friendShip) {
            final Tuple<User, User> users = friendShip.getUsers();
            return friendShip.getCreationDate() + ";" +
                    users.left().getId() + ";" +
                    users.right().getId() + "\n";
        }

        @FunctionalInterface
        public interface Resource {

            @NotNull ArrayList<User> getUsers() throws SQLException;
        }
    }
}

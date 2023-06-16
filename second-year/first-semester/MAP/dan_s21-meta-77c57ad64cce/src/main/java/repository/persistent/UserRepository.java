package repository.persistent;

import domain.model.User;
import domain.util.Serializer;
import org.jetbrains.annotations.NotNull;

public class UserRepository extends FileRepository<Long, User> {

    public UserRepository(@NotNull final String fileName) {
        super(fileName, new UserSerializer());
    }

    private final static class UserSerializer extends Serializer<User> {
        @Override
        public @NotNull User fromString(@NotNull final String line) {
            final String[] attributes = line.split(";");
            final long id = Long.parseLong(attributes[0]);
            return new User(id, attributes[1], attributes[2]);
        }

        @Override
        public @NotNull String toString(@NotNull final User user) {
            return user.getId() + ";" + user.getFirstName() + ";" + user.getLastName() + "\n";
        }
    }
}

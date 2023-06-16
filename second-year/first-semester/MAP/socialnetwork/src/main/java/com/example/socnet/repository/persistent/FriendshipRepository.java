package com.example.socnet.repository.persistent;

import com.example.socnet.domain.data.MetaDB;
import com.example.socnet.domain.data.table.FriendshipTable;
import com.example.socnet.domain.data.table.dto.FriendshipDTO;
import com.example.socnet.domain.exceptions.InvalidException;
import com.example.socnet.domain.model.Friendship;
import com.example.socnet.domain.model.Tuple;
import com.example.socnet.domain.model.User;
import com.example.socnet.domain.util.Serializer;
import com.example.socnet.repository.memory.Repository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class FriendshipRepository extends Repository<Long, Friendship> {

    final FriendshipTable table;
    final FriendshipSerializer.Resource resource;

    public FriendshipRepository(@NotNull final FriendshipSerializer.Resource resource)
            throws SQLException {
        this.table = (FriendshipTable) MetaDB.getInstance().table("friendships");
        this.resource = resource;
        for (final Friendship friendship : this.getAll()) {
            super.add(friendship);
        }
    }

    @Override
    public @NotNull Optional<Friendship> add(@NotNull Friendship entity) throws SQLException {
        final Optional<Friendship> result =  super.add(entity);
        if (result.isPresent()) {
            this.table.insert(
                    new FriendshipDTO(
                            entity.getCreationDate(),
                            entity.getUsers().left().getId(),
                            entity.getUsers().right().getId()
                    )
            );
        }
        return result;
    }

    @Override
    public @NotNull Optional<Friendship> update(@NotNull Friendship entity) throws SQLException {
        return super.update(entity);
    }

    @Override
    public @NotNull Optional<Friendship> findByID(@NotNull final Long id) throws SQLException {
        final Optional<FriendshipDTO> result = this.table.whereID(id);
        return result.isPresent()
                ? Optional.of(this.fromDTO(result.get()))
                : Optional.empty();
    }

    @Override
    public @NotNull Optional<Friendship> delete(@NotNull Long id) throws SQLException {
        final Optional<Friendship> result = super.delete(id);
        if (result.isPresent()) {
            this.table.delete(id);
        }
        return result;
    }

    @Override
    public @NotNull ArrayList<Friendship> getAll() throws SQLException {
        final ArrayList<FriendshipDTO> friendshipDTOs = this.table.getAll();
        final ArrayList<Friendship> friendships = new ArrayList<>();
        for (final FriendshipDTO dto : friendshipDTOs) {
            friendships.add(this.fromDTO(dto));
        }
        return friendships;
    }

    @NotNull
    private Friendship fromDTO(@NotNull final FriendshipDTO dto) throws SQLException {
        User left = null;
        User right = null;
        for (final User user : this.resource.getUsers()) {
            if (user.getId() != null &&
                    (user.getId().equals(dto.firstID()) ||
                            user.getId().equals(dto.secondID()))) {
                if (left != null) {
                    right = user;
                    break;
                } else {
                    left = user;
                }
            }
        }
        if (left == null || right == null) {
            throw new InvalidException("invalid users");
        }
        final Tuple<User, User> userTuple = new Tuple<>(left, right);
        return new Friendship(getIdFrom(userTuple), dto.creationDate(), userTuple);
    }
    /**
     * @param users user tuple
     * @return the sum of the ids of the users
     */
    @Nullable
    public Long getIdFrom(@NotNull final Tuple<User, User> users) {
        final Long userId1 = users.left().getId();
        final Long userId2 = users.right().getId();
        if (userId1 == null || userId2 == null) {
            return null;
        }
        return userId1 + userId2;
    }

    public final static class FriendshipSerializer extends Serializer<Friendship> {

        @NotNull private final FriendshipSerializer.Resource resource;

        public FriendshipSerializer(@NotNull FriendshipSerializer.Resource resource) {
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

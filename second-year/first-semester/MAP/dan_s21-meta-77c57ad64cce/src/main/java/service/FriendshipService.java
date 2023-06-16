package service;

import domain.model.Friendship;
import domain.model.Tuple;
import domain.model.User;
import domain.validation.FriendshipValidator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import repository.Repository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public record FriendshipService(@NotNull FriendshipValidator validator,
                                @NotNull Repository<Long, Friendship> repository) {

    /**
     * @param user user who will take part in this friendship
     * @param other second user
     */
    public void add(@NotNull final User user, @NotNull final User other) throws SQLException {
        final LocalDateTime currentTimestamp = LocalDateTime.now();
        final Tuple<User, User> users = new Tuple<>(user, other);
        @Nullable final Long id = this.getIdFrom(users);
        final Friendship friendship = new Friendship(id, currentTimestamp, users);
        this.validator.validate(friendship);
        this.repository.add(friendship);
    }

    /**
     * @param users user tuple
     * @return the sum of the ids of the users
     */
    @Nullable
    private Long getIdFrom(@NotNull final Tuple<User, User> users) {
        final Long userId1 = users.left().getId();
        final Long userId2 = users.right().getId();
        if (userId1 == null || userId2 == null) {
            return null;
        }
        return userId1 + userId2;
    }

    /**
     * @param id the id of the friendship we want to delete
     */
    public void delete(final long id) throws SQLException {
        this.repository.delete(id);
    }

    /**
     * @param id the id of a friendship
     * @return Optional.of(FriendShip) if the friendship with the specified id
     * has been found, or Optional.empty() if not
     */
    @NotNull
    public Optional<Friendship> findByID(final long id) throws SQLException {
        return this.repository.findByID(id);
    }

    /**
     * @return a list which contains all the friendships in Meta
     */
    @NotNull
    public ArrayList<Friendship> getAll() throws SQLException {
        return this.repository.getAll();
    }

    /**
     * @param user the user for who will be searched all the friendships
     * @return a list with the friendships that contains the given user
     */
    @NotNull
    public ArrayList<Friendship> getAll(@NotNull final User user) throws SQLException {
        final ArrayList<Friendship> friendships = this.repository.getAll();
        final ArrayList<Friendship> result = new ArrayList<>();

        for (@NotNull final Friendship friendship : friendships) {
            final Tuple<User, User> users = friendship.getUsers();
            if (Objects.equals(user.getId(), users.left().getId()) ||
                    Objects.equals(user.getId(), users.right().getId())) {
                result.add(friendship);
            }
        }
        return result;
    }
}

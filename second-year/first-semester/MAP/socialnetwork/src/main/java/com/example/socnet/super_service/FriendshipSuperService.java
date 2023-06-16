package com.example.socnet.super_service;

import com.example.socnet.domain.model.Friendship;
import com.example.socnet.domain.model.Tuple;
import com.example.socnet.domain.model.User;
import com.example.socnet.service.CurrentUser;
import com.example.socnet.service.FriendshipService;
import com.example.socnet.service.UserService;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


public record FriendshipSuperService(@NotNull UserService userService,
                                     @NotNull FriendshipService friendShipService) {

    public void add(final long id, final long other) {
        try {
            if (id == other) {
                return;
            }

            final Optional<User> searchResult1 = this.userService.findByID(id);
            final Optional<User> searchResult2 = this.userService.findByID(other);
            if (searchResult1.isEmpty() || searchResult2.isEmpty()) {
                return;
            }
            this.friendShipService.add(searchResult1.get(), searchResult2.get());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @NotNull
    public Optional<Friendship> findByID(final long id) throws SQLException {
        return this.friendShipService.findByID(id);
    }

    public Long areFriends(User user1, User user2) throws SQLException {
        List<Friendship> list = friendShipService.getAll().stream().filter(x-> ((Objects.equals(x.getUsers().left().getId(), user1.getId()) &&
                Objects.equals(x.getUsers().right().getId(), user2.getId())) ||
                (Objects.equals(x.getUsers().left().getId(), user2.getId()) && Objects.equals(x.getUsers().right().getId(), user1.getId())))).collect(Collectors.toList());
        if (list.size() != 0)
            return list.get(0).getId();
        return 0L;
    }

    /**
     * @param id the id of the friendship that will be deleted
     */
    public void delete(final long id) throws SQLException {
        this.friendShipService.delete(id);
    }

    @NotNull
    public ArrayList<Friendship> getAll() throws SQLException {
        return this.friendShipService.getAll();
    }

    @NotNull
    public List<User> getMyFriends()
            throws SQLException {
        final long userId = CurrentUser.getInstance().getUser().getId();
        return friendShipService
                .getAll()
                .stream()
                .filter(friendship -> {
                    final Tuple<User, User> users = friendship.getUsers();
                    return users.left().getId() == userId ||
                            users.right().getId() == userId;
                })
                .map(friendship -> {
                    final Tuple<User, User> users = friendship.getUsers();
                    final long otherId = users.left().getId().equals(userId)
                            ? users.right().getId()
                            : users.left().getId();
                    return userService.findByID(otherId).get();
                })
                .toList();
    }
}

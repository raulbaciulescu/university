package com.example.socialapp.service;

import com.example.socialapp.domain.Friendship;
import com.example.socialapp.domain.Tuple;
import com.example.socialapp.domain.User;
import com.example.socialapp.utils.CurrentUser;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public record FriendshipSuperService(UserService userService,
                                     FriendshipService friendShipService) {

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

    public Optional<Friendship> findByID(final long id) throws SQLException {
        return Optional.empty();
        //return this.friendShipService.findByID(id);
    }

    public Optional<Tuple<Long, Long>> areFriends(User user1, User user2) throws SQLException {
        Optional<Friendship> optionalFriendship = friendShipService.findByID(new Tuple<Long, Long>(user1.getId(), user2.getId()));
        Optional<Friendship> optionalFriendship2 = friendShipService.findByID(new Tuple<Long, Long>(user2.getId(), user1.getId()));

        if (optionalFriendship.isPresent())
            return Optional.of(optionalFriendship.get().getId());
        if (optionalFriendship.isPresent())
            return Optional.of(optionalFriendship2.get().getId());
        return Optional.empty();
    }

    public void delete(final Tuple<Long, Long> tuple) throws SQLException {
        friendShipService.delete(tuple);
    }

    public ArrayList<Friendship> getAll() throws SQLException {
        return this.friendShipService.getAll();
    }

    public List<User> getMyFriends()
            throws SQLException {
        final long userId = CurrentUser.getInstance().getUser().get().getId();
        return friendShipService
                .getAll()
                .stream()
                .filter(friendship -> {
                    final Tuple<User, User> users = friendship.getUsers();
                    return users.first().getId() == userId ||
                            users.second().getId() == userId;
                })
                .map(friendship -> {
                    final Tuple<User, User> users = friendship.getUsers();
                    final long otherId = users.first().getId().equals(userId)
                            ? users.second().getId()
                            : users.first().getId();
                    try {
                        return userService.findByID(otherId).get();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .toList();
    }
}

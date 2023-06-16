package com.example.socialapp.service;


import com.example.socialapp.domain.Friendship;
import com.example.socialapp.domain.FriendshipValidator;
import com.example.socialapp.domain.Tuple;
import com.example.socialapp.domain.User;
import com.example.socialapp.repository.Repository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record FriendshipService(FriendshipValidator validator,
                                Repository<Tuple<Long, Long>, Friendship> repository) {

    public void add(final User first, final User second) throws SQLException {
        final LocalDateTime currentTimestamp = LocalDateTime.now();
        final Tuple<User, User> users = new Tuple<>(first, second);
        final Friendship friendship = new Friendship(currentTimestamp, users);
        this.validator.validate(friendship);
        this.repository.add(friendship);
    }

    public void delete(final Tuple<Long, Long> tuple) throws SQLException {
        this.repository.delete(tuple);
    }


    public Optional<Friendship> findByID(final Tuple<Long, Long> tuple) throws SQLException {
        return this.repository.findByID(tuple);
    }


    public ArrayList<Friendship> getAll() throws SQLException {
        return this.repository.getAll();
    }

}

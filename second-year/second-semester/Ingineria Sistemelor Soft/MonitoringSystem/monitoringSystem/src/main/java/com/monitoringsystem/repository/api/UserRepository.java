package com.monitoringsystem.repository.api;

import com.monitoringsystem.model.User;

import java.util.Optional;

public interface UserRepository extends Repository<Long, User> {
    Optional<User> findUser(User user);
}

package com.monitoringsystem.service.api;

import com.monitoringsystem.model.User;
import com.monitoringsystem.utils.Observable;

import java.util.List;
import java.util.Optional;

public interface UserService extends Observable {
    void add(String username, String password, String firstName, String lastName, Integer role);
    void delete(Long id);
    Optional<User> findUser(String username, String password);
    List<User> getAll();

    void logout();
}

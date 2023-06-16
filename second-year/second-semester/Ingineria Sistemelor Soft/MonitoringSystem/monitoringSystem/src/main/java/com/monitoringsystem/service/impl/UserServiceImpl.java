package com.monitoringsystem.service.impl;

import com.monitoringsystem.controller.LoggedUser;
import com.monitoringsystem.model.User;
import com.monitoringsystem.repository.api.UserRepository;
import com.monitoringsystem.service.api.UserService;
import com.monitoringsystem.utils.Observable;
import com.monitoringsystem.utils.Resources;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final Random random;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        random = new Random();
    }

    @Override
    public void add(String username, String password, String firstName, String lastName, Integer role) {
        long id = random.nextLong();
        userRepository.add(new User(id, username, password, firstName, lastName, role));
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public Optional<User> findUser(String username, String password) {
        Optional<User> optional = userRepository.findUser(new User(username, password));
        if (optional.isPresent() && optional.get().getRole() == 0) {
            try {
                Resources.getInstance().addUser(new LoggedUser(optional.get(), LocalTime.now()));
                notifyUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return optional;
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public void logout() {
        notifyUpdate();
    }
}

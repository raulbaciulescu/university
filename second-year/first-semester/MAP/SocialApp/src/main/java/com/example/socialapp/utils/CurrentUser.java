package com.example.socialapp.utils;

import com.example.socialapp.domain.User;

import java.util.Optional;

public class CurrentUser {
    private User user;
    private static CurrentUser instance = null;

    private CurrentUser() {
        user = null;
    }

    public static CurrentUser getInstance() {
        if (instance == null)
            instance = new CurrentUser();
        return instance;
    }

    public Optional<User> getUser() {
        return Optional.of(user);
    }

    public void setUser(User user) {
        this.user = user;
    }
}

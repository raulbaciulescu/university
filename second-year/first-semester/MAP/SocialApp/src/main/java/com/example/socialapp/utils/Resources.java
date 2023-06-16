package com.example.socialapp.utils;

import com.example.socialapp.repository.FriendshipRepository;
import com.example.socialapp.repository.UserRepository;
import com.example.socialapp.service.FriendshipService;
import com.example.socialapp.service.UserService;

import java.sql.SQLException;

public final class Resources {
    public static Resources instance = null;
    public static UserService userService;
    public static UserRepository userRepo;
    public static FriendshipRepository friendshipRepo;
    public static FriendshipService friendshipService;

    private Resources() throws SQLException {
        userRepo = new UserRepository();
        friendshipRepo = new FriendshipRepository();
        userService = new UserService(userRepo);
    }


    public static Resources getInstance() throws SQLException {
        if (instance == null) {
            instance = new Resources();
        }
        return instance;
    }
}

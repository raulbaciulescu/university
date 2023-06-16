package com.monitoringsystem.utils;


import com.monitoringsystem.controller.LoggedUser;
import com.monitoringsystem.model.User;
import com.monitoringsystem.repository.api.TaskRepository;
import com.monitoringsystem.repository.api.UserRepository;
import com.monitoringsystem.repository.impl.TaskRepositoryImpl;
import com.monitoringsystem.repository.impl.UserRepositoryImpl;
import com.monitoringsystem.service.api.TaskService;
import com.monitoringsystem.service.api.UserService;
import com.monitoringsystem.service.impl.TaskServiceImpl;
import com.monitoringsystem.service.impl.UserServiceImpl;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.*;

public class Resources {
    private static Resources instance = null;
    private final UserService userService;
    private final TaskService taskService;
    private final UserRepository userRepo;
    private final TaskRepository taskRepo;
    private List<LoggedUser> loggedUsers;
    private User lastLoggedUser;
    private User boss;

    public static Resources getInstance() throws SQLException {
        if (instance == null) {
            instance = new Resources();
        }
        return instance;
    }

    private Resources() {
        userRepo = new UserRepositoryImpl();
        taskRepo = new TaskRepositoryImpl();
        userService = new UserServiceImpl(userRepo);
        taskService = new TaskServiceImpl(taskRepo);
        loggedUsers = new ArrayList<>();
    }

    public UserService getUserService() {
        return userService;
    }

    public UserRepository getUserRepo() {
        return userRepo;
    }

    public User getLastLoggedUser() {
        return lastLoggedUser;
    }

    public void setLastLoggedUser(User loggedUser) {
        this.lastLoggedUser = loggedUser;
    }

    public User getBoss() {
        return boss;
    }

    public void setBoss(User boss) {
        this.boss = boss;
    }

    public TaskService getTaskService() {
        return taskService;
    }

    public void addUser(LoggedUser loggedUser) {
        loggedUser.setLoginDateString(DateUtils.stringifyLocalTime(loggedUser.getLoginDate()));
        loggedUsers.add(loggedUser);
    }

    public void deleteUser(LoggedUser loggedUser) {
        List<LoggedUser> newLoggedUsers = new ArrayList<>();
        for (LoggedUser user : loggedUsers) {
            if (!Objects.equals(user.getId(), loggedUser.getId()))
                newLoggedUsers.add(user);
        }
        loggedUsers = newLoggedUsers;
    }

    public List<LoggedUser> getLoggedUsers() {
        return loggedUsers;
    }
}

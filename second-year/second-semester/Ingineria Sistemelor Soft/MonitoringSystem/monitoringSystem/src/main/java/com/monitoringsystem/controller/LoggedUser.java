package com.monitoringsystem.controller;

import com.monitoringsystem.model.User;

import java.time.LocalDate;
import java.time.LocalTime;

public class LoggedUser extends User {
    private LocalTime loginDate;
    private String loginDateString;

    public LoggedUser(Long id, String username,
                String password, String firstName, String lastName, Integer role, LocalTime loginDate) {
        super(id, username, password, firstName, lastName, role);
        this.loginDate = loginDate;
    }

    public String getLoginDateString() {
        return loginDateString;
    }

    public void setLoginDateString(String loginDateString) {
        this.loginDateString = loginDateString;
    }

    public LoggedUser(User user, LocalTime loginDate) {
        super(user.getId(), user.getUsername(), user.getPassword(), user.getFirstName(),
                user.getLastName(), user.getRole());
        this.loginDate = loginDate;
    }

    public User getUser() {
        return new User(getId(), getUsername(), getPassword(), getFirstName(), getLastName(), getRole());
    }

    public LocalTime getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(LocalTime loginDate) {
        this.loginDate = loginDate;
    }

    @Override
    public String toString() {
        return "LoggedUser{" + super.toString() + " " +
                "loginDate=" + loginDate +
                '}';
    }
}

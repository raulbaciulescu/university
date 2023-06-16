package com.example.socnet.domain.model;

public class UserLogin extends Entity<Long> {

    private final long userID;
    private final String username;
    private final String hashedPassword;

    public UserLogin(final long userID,
                     final String username,
                     final String hashedPassword) {
        this.userID = userID;
        this.username = username;
        this.hashedPassword = hashedPassword;
    }

    public long getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }
}

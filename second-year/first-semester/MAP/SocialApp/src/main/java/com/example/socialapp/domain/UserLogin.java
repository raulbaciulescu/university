package com.example.socialapp.domain;

import com.example.socialapp.utils.PasswordAuthentication;

public class UserLogin extends Entity<Long> {
    private long userId;
    private String username;
    private String hashedPassword;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserLogin(String username, String hashedPassword, Long userId) {
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
}

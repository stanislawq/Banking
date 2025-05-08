package com.banking.model;

import com.banking.utils.PasswordEncoder;

public class UserData {
    private final String username;
    private final String passwordHash;

    public UserData(String username, String password) {
        this.username = username;
        this.passwordHash = PasswordEncoder.encode(password);
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "username='" + username + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }
}

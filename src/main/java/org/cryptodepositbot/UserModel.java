package org.cryptodepositbot;

import java.time.LocalDateTime;

public class UserModel {

    private final String userId;
    private final String username;
    private String firstName;
    private LocalDateTime createdAt;
    private String role;

    public UserModel(String userId, String username, LocalDateTime createdAt, String role){
        this.userId = userId;
        this.username = username;
        this.createdAt = createdAt;
        this.role = role;
    }

    public UserModel(String userId, String username, String firstName) {
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getRole() {
        return role;
    }
}

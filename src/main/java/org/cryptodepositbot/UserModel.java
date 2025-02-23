package org.cryptodepositbot;

import java.time.LocalDateTime;

public class UserModel {

    private final long telegramId;
    private final String username;
    private String firstName;
    private String chatId;
    private LocalDateTime createdAt;
    private String role;

    public UserModel(long telegramId, String username, LocalDateTime createdAt, String role){
        this.telegramId = telegramId;
        this.username = username;
        this.createdAt = createdAt;
        this.role = role;
    }

    public UserModel(long telegramId, String username, String chatId, String firstName) {
        this.telegramId = telegramId;
        this.username = username;
        this.chatId = chatId;
        this.firstName = firstName;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getChatId() {
        return chatId;
    }

    public long getTelegramId() {
        return telegramId;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getRole() {
        return role;
    }
}

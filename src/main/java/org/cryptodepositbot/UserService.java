package org.cryptodepositbot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.cryptodepositbot.Database.connect;

public class UserService {
    public static void createUser(long telegramId, String username) {
        String sql = """
                INSERT INTO users (telegram_id, username)
                VALUES (?, ?);
                """;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            System.out.println();
            pstmt.setString(1, String.valueOf(telegramId));
            pstmt.setString(2, username);
            pstmt.executeUpdate();
            System.out.println("✅ Пользователь добавлен: " + username);
        } catch (SQLException e) {
            System.out.println("Ошибка добавления пользователя: " + e.getMessage());
        }
    }
    public static boolean isRegistered(long telegramId) {
        String sql = """
                SELECT telegram_id, username
                FROM users
                WHERE telegram_id = ?
                """;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, telegramId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String username = rs.getString("username");
                System.out.println("✅ Пользователь уже зарегистрирован: " + username);
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Ошибка добавления пользователя: " + e.getMessage());
        }

        return false;
    }
}

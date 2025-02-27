package org.cryptodepositbot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.cryptodepositbot.Database.connect;

public class UserService {
    public static void createUser(String userId, String username) {
        String sql = """
                INSERT INTO users (user_id, username)
                VALUES (?, ?);
                """;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            System.out.println();
            pstmt.setString(1, userId);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
            System.out.println("✅ Пользователь добавлен: " + username);
        } catch (SQLException e) {
            System.out.println("❌ Ошибка добавления пользователя: " + e.getMessage());
        }
    }
    public static boolean isRegistered(String userId) {
        String sql = """
                SELECT user_id, username
                FROM users
                WHERE user_id = ?
                """;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String username = rs.getString("username");
                System.out.println("✅ Пользователь уже зарегистрирован: " + username);
                return true;
            }
        } catch (SQLException e) {
            System.out.println("❌ Ошибка проверки пользователя: " + e.getMessage());
        }

        return false;
    }
}

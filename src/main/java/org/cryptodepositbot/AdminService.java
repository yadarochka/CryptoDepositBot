package org.cryptodepositbot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.cryptodepositbot.Database.connect;

public class AdminService {
    public static ArrayList<UserModel> showAllUsers() {
        ArrayList<UserModel> userList = new ArrayList<>();

        String sql = """
                SELECT telegram_id, username, created_at, roles.name as role
                FROM users
                INNER JOIN roles on roles.id = users.role_id;
                """;

        try (Connection connection = connect(); PreparedStatement psms = connection.prepareStatement(sql)) {
            ResultSet rs = psms.executeQuery();

            while (rs.next()) {
                long telegramId = Long.parseLong(rs.getString("telegram_id"));
                String userName = rs.getString("userName");
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
                String role = rs.getString("role");

                userList.add(new UserModel(telegramId, userName, createdAt, role));
            }

            return userList;

        } catch (SQLException e) {
            System.out.println("Ошибка показа всех пользователей: " + e.getMessage());
        }

        System.out.println("userList = " + userList);

        return userList;
    }

    public static boolean isAdmin(long telegramId) {

        String sql = """
                SELECT role_id
                FROM users
                WHERE telegram_id = ?
                """;

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, telegramId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("role_id") == 2;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}

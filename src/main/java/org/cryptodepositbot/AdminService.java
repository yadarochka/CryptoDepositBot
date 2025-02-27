package org.cryptodepositbot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.cryptodepositbot.Database.connect;

public class AdminService {
    public static ArrayList<UserModel> showAllUsers() {
        ArrayList<UserModel> userList = new ArrayList<>();

        String sql = """
                SELECT user_id, username, created_at, roles.name as role
                FROM users
                INNER JOIN roles on roles.id = users.role_id;
                """;

        try (Connection connection = connect(); PreparedStatement psms = connection.prepareStatement(sql)) {
            ResultSet rs = psms.executeQuery();

            while (rs.next()) {
                String userId = rs.getString("user_id");
                String userName = rs.getString("userName");
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
                String role = rs.getString("role");

                userList.add(new UserModel(userId, userName, createdAt, role));
            }

            return userList;

        } catch (SQLException e) {
            System.out.println("Ошибка показа всех пользователей: " + e.getMessage());
        }

        System.out.println("userList = " + userList);

        return userList;
    }

    public static boolean isAdmin(String userId) {

        String sql = """
                SELECT role_id
                FROM users
                WHERE user_id = ?
                """;

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("role_id") == 2;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static ArrayList<String> getAdminsChatIds(){
        ArrayList<String> adminChatIdsList = new ArrayList<>();
        String sql = """
                SELECT user_id
                FROM users
                WHERE role_id = 2
                """;
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String adminId = rs.getString("user_id");
                System.out.println(adminId);
                adminChatIdsList.add(adminId);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return adminChatIdsList;
    }
}

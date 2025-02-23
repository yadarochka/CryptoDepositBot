package org.cryptodepositbot;

import java.sql.*;

public class Database {
    private static final String URL = "jdbc:sqlite:database.db";

    public static Connection connect() {
        try {
            System.out.print("\uD83D\uDD04 Попытка подключения к бд...\t");
            Connection connection = DriverManager.getConnection(URL);

            if (connection == null) {
                throw new IllegalStateException("❌ Не удалось подключиться к базе данных!");
            }
            System.out.println("✅ БД подключена!");
            return connection;

        } catch (SQLException e) {
            throw new RuntimeException("❌ Ошибка: " + e.getMessage());
        }
    }

    public static void createTables() {

        String createRolesTable = """
                CREATE TABLE IF NOT EXISTS roles (
                    id INTEGER PRIMARY KEY,
                    name TEXT UNIQUE NOT NULL
                );
                """;

        String createUsersTable = """
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    telegram_id TEXT UNIQUE NOT NULL,
                    role_id INTEGER NOT NULL DEFAULT 1,
                    username TEXT,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
                );
                """;

        String createDepositsTable = """
                CREATE TABLE IF NOT EXISTS deposits (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    user_id INTEGER NOT NULL,              -- ID пользователя (из users)
                    initial_deposit REAL NOT NULL,
                    interest_rate INTEGER DEFAULT 5, -- 0.5% в день
                    accrued_interest REAL DEFAULT 0,
                    start_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- Дата внесения депозита
                    last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Последнее обновление процентов
                    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
                );
                """;

        String insertToRolesTable = """
                INSERT INTO roles (id, name) values (1, 'user'), (2, 'admin');
                """;

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {

            stmt.execute(createRolesTable);
            System.out.println("✅ Таблица roles создана!");
            stmt.execute(createUsersTable);
            System.out.println("✅ Таблица user создана!");
            stmt.execute(createDepositsTable);
            System.out.println("✅ Таблица deposits создана!");
            stmt.execute(insertToRolesTable);
            System.out.println("✅ Роли добавлены!");

        } catch (SQLException e) {
            System.out.println("Ошибка при проверке/создании таблиц: " + e.getMessage());
        }
    }
}

package com.threadforge.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static final String URL = "jdbc:sqlite:threadforge.db";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public void initialize() {

        String sql = """
                CREATE TABLE IF NOT EXISTS jobs (
                    job_id TEXT PRIMARY KEY,
                    job_name TEXT NOT NULL,
                    job_type TEXT NOT NULL,
                    priority TEXT NOT NULL,
                    status TEXT NOT NULL,
                    worker_id TEXT,
                    created_at TEXT,
                    started_at TEXT,
                    completed_at TEXT
                )
                """;

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
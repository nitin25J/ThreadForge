package com.threadforge.database;

import com.threadforge.model.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WorkerRepository {

    private final DatabaseManager databaseManager;

    public WorkerRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public void save(Worker worker) {

        String createTable = """
                CREATE TABLE IF NOT EXISTS workers (
                    worker_id TEXT PRIMARY KEY,
                    worker_name TEXT NOT NULL,
                    busy INTEGER NOT NULL
                )
                """;

        String insert = """
                INSERT OR REPLACE INTO workers
                (worker_id, worker_name, busy)
                VALUES (?, ?, ?)
                """;

        try (Connection connection = databaseManager.getConnection()) {

            try (var statement = connection.createStatement()) {
                statement.executeUpdate(createTable);
            }

            try (PreparedStatement statement = connection.prepareStatement(insert)) {
                statement.setString(1, worker.getWorkerId());
                statement.setString(2, worker.getWorkerName());
                statement.setInt(3, worker.isBusy() ? 1 : 0);

                statement.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Could not save worker: " + e.getMessage());
        }
    }
}
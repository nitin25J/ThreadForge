package com.threadforge.model;

import com.threadforge.database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseJob extends Job {

    private final String query;
    private final DatabaseManager databaseManager;

    public DatabaseJob(String jobId, String jobName, JobPriority priority, String query) {
        super(jobId, jobName, priority);
        this.query = query;
        this.databaseManager = new DatabaseManager();
    }

    public String getQuery() {
        return query;
    }

    @Override
    public void execute() {
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            boolean hasResult = statement.execute();

            if (hasResult) {
                try (ResultSet result = statement.getResultSet()) {
                    while (result.next()) {
                        System.out.println(result.getString(1));
                    }
                }
            } else {
                System.out.println("Database query executed successfully.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Database query failed: " + e.getMessage());
        }
    }

    @Override
    public String getJobType() {
        return "DATABASE";
    }
}
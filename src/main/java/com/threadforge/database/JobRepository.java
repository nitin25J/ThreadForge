package com.threadforge.database;

import com.threadforge.model.Job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobRepository {

    private final DatabaseManager databaseManager;

    public JobRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public void save(Job job) {
        String sql = """
                INSERT OR REPLACE INTO jobs
                (job_id, job_name, job_type, priority, status, worker_id,
                 created_at, started_at, completed_at)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = databaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, job.getJobId());
            statement.setString(2, job.getJobName());
            statement.setString(3, job.getJobType());
            statement.setString(4, job.getPriority().name());
            statement.setString(5, job.getStatus().name());
            statement.setString(6, job.getWorkerId());
            statement.setString(7, job.getCreatedAt().toString());
            statement.setString(8, job.getStartedAt() == null
                    ? null : job.getStartedAt().toString());
            statement.setString(9, job.getCompletedAt() == null
                    ? null : job.getCompletedAt().toString());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Could not save job: " + e.getMessage());
        }
    }

    public List<String> getJobHistory() {
        List<String> history = new ArrayList<>();

        String sql = """
                SELECT job_id, job_name, job_type, priority, status
                FROM jobs
                ORDER BY created_at
                """;

        try (Connection connection = databaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                String job = result.getString("job_id") + " | "
                        + result.getString("job_name") + " | "
                        + result.getString("job_type") + " | "
                        + result.getString("priority") + " | "
                        + result.getString("status");

                history.add(job);
            }

        } catch (SQLException e) {
            System.out.println("Could not read job history: " + e.getMessage());
        }

        return history;
    }
}
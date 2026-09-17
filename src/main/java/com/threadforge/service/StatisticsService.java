package com.threadforge.service;

import com.threadforge.model.Job;
import com.threadforge.model.JobStatus;

import java.util.Collection;

public class StatisticsService {

    public int totalJobs(Collection<Job> jobs) {
        return jobs.size();
    }

    public int countByStatus(Collection<Job> jobs, JobStatus status) {
        int count = 0;

        for (Job job : jobs) {
            if (job.getStatus() == status) {
                count++;
            }
        }

        return count;
    }

    public double averageExecutionTime(Collection<Job> jobs) {
        long total = 0;
        int completed = 0;

        for (Job job : jobs) {
            if (job.getStatus() == JobStatus.COMPLETED) {
                total += job.getExecutionTimeMillis();
                completed++;
            }
        }

        if (completed == 0) {
            return 0;
        }

        return (double) total / completed;
    }
}
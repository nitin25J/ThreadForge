package com.threadforge.model;

import com.threadforge.interfaces.Cancellable;
import com.threadforge.interfaces.Executable;

import java.time.LocalDateTime;

public abstract class Job implements Executable, Cancellable {

    private final String jobId;
    private final String jobName;
    private final JobPriority priority;

    private JobStatus status;
    private final LocalDateTime createdAt;

    private LocalDateTime startedAt;
    private LocalDateTime completedAt;

    private String workerId;
    private String errorMessage;

    protected Job(String jobId, String jobName, JobPriority priority) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.priority = priority;
        this.status = JobStatus.QUEUED;
        this.createdAt = LocalDateTime.now();
    }

    public String getJobId() {
        return jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public JobPriority getPriority() {
        return priority;
    }

    public JobStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public String getWorkerId() {
        return workerId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void markRunning(String workerId) {
        this.workerId = workerId;
        this.status = JobStatus.RUNNING;
        this.startedAt = LocalDateTime.now();
    }

    public void markCompleted() {
        this.status = JobStatus.COMPLETED;
        this.completedAt = LocalDateTime.now();
    }

    public void markFailed(String errorMessage) {
        this.status = JobStatus.FAILED;
        this.errorMessage = errorMessage;
        this.completedAt = LocalDateTime.now();
    }

    @Override
    public void cancel() {
        if (status == JobStatus.QUEUED || status == JobStatus.RUNNING) {
            status = JobStatus.CANCELLED;
            completedAt = LocalDateTime.now();
        }
    }

    public long getExecutionTimeMillis() {
        if (startedAt == null || completedAt == null) {
            return 0;
        }

        return java.time.Duration.between(startedAt, completedAt).toMillis();
    }

    public abstract String getJobType();

    @Override
    public String toString() {
        return "Job{" +
                "jobId='" + jobId + '\'' +
                ", jobName='" + jobName + '\'' +
                ", priority=" + priority +
                ", status=" + status +
                ", workerId='" + workerId + '\'' +
                '}';
    }
}
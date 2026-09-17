package com.threadforge.engine;

import com.threadforge.database.JobRepository;
import com.threadforge.model.Job;
import com.threadforge.model.JobStatus;
import com.threadforge.model.Worker;
import com.threadforge.util.LoggerUtil;

public class JobExecutor {

    private final JobRepository repository;

    public JobExecutor(JobRepository repository) {
        this.repository = repository;
    }

    public void execute(Job job, Worker worker) {

        if (job.getStatus() == JobStatus.CANCELLED) {
            return;
        }

        try {
            worker.setBusy(true);
            job.markRunning(worker.getWorkerId());

            repository.save(job);
            LoggerUtil.info(job.getJobId() + " started by " + worker.getWorkerId());

            job.execute();

            if (job.getStatus() == JobStatus.RUNNING) {
                job.markCompleted();
            }

            repository.save(job);
            LoggerUtil.info(job.getJobId() + " completed");

        } catch (Exception e) {
            job.markFailed(e.getMessage());
            repository.save(job);
            LoggerUtil.error(job.getJobId() + " failed: " + e.getMessage());

        } finally {
            worker.setBusy(false);
        }
    }
}
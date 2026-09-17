package com.threadforge.service;

import com.threadforge.database.JobRepository;
import com.threadforge.engine.ThreadForgeEngine;
import com.threadforge.model.Job;
import com.threadforge.model.JobStatus;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JobService {

    private final ThreadForgeEngine engine;
    private final JobRepository repository;
    private final Map<String, Job> jobs = new ConcurrentHashMap<>();

    public JobService(ThreadForgeEngine engine, JobRepository repository) {
        this.engine = engine;
        this.repository = repository;
    }

    public void submit(Job job) {
        jobs.put(job.getJobId(), job);
        repository.save(job);
        engine.submitJob(job);
    }

    public Job getJob(String jobId) {
        return jobs.get(jobId);
    }

    public Map<String, Job> getAllJobs() {
        return jobs;
    }

    public boolean cancelJob(String jobId) {
        Job job = jobs.get(jobId);

        if (job == null) {
            return false;
        }

        if (job.getStatus() != JobStatus.QUEUED &&
                job.getStatus() != JobStatus.RUNNING) {
            return false;
        }

        job.cancel();
        repository.save(job);
        return true;
    }
}
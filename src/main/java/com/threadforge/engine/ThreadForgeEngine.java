package com.threadforge.engine;

import com.threadforge.database.JobRepository;
import com.threadforge.model.Job;

public class ThreadForgeEngine {

    private final JobScheduler scheduler;
    private final WorkerPool workerPool;

    public ThreadForgeEngine(int workerCount, JobRepository repository) {
        scheduler = new JobScheduler();
        workerPool = new WorkerPool(scheduler, repository, workerCount);
    }

    public void start() {
        workerPool.start();
    }

    public void submitJob(Job job) {
        scheduler.addJob(job);
    }

    public int getQueueSize() {
        return scheduler.size();
    }

    public void stop() {
        workerPool.stop();
    }

    public JobScheduler getScheduler() {
        return scheduler;
    }

    public WorkerPool getWorkerPool() {
        return workerPool;
    }
}
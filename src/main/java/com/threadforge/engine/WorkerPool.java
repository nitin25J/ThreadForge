package com.threadforge.engine;

import com.threadforge.database.JobRepository;
import com.threadforge.model.Job;
import com.threadforge.model.Worker;

import java.util.ArrayList;
import java.util.List;

public class WorkerPool {

    private final List<Worker> workers = new ArrayList<>();
    private final JobScheduler scheduler;
    private final JobExecutor executor;
    private final List<Thread> threads = new ArrayList<>();

    public WorkerPool(JobScheduler scheduler, JobRepository repository, int workerCount) {
        this.scheduler = scheduler;
        this.executor = new JobExecutor(repository);

        for (int i = 1; i <= workerCount; i++) {
            workers.add(new Worker("W-" + i, "Worker-" + i));
        }
    }

    public void start() {
        for (Worker worker : workers) {
            Thread thread = new Thread(() -> runWorker(worker));
            thread.setName(worker.getWorkerId());
            threads.add(thread);
            thread.start();
        }
    }

    private void runWorker(Worker worker) {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Job job = scheduler.getNextJob();
                executor.execute(job, worker);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void stop() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}
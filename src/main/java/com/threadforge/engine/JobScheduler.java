package com.threadforge.engine;

import com.threadforge.model.Job;
import com.threadforge.model.JobPriority;

import java.util.Comparator;
import java.util.PriorityQueue;

public class JobScheduler {

    private final PriorityQueue<Job> jobs;

    public JobScheduler() {
        jobs = new PriorityQueue<>(
                Comparator.comparingInt(
                        (Job job) -> priorityValue(job.getPriority())
                ).reversed()
        );
    }

    public synchronized void addJob(Job job) {
        jobs.offer(job);
        notifyAll();
    }

    public synchronized Job getNextJob() throws InterruptedException {
        while (jobs.isEmpty()) {
            wait();
        }

        return jobs.poll();
    }

    public synchronized int size() {
        return jobs.size();
    }

    private int priorityValue(JobPriority priority) {
        return switch (priority) {
            case LOW -> 1;
            case MEDIUM -> 2;
            case HIGH -> 3;
            case CRITICAL -> 4;
        };
    }
}
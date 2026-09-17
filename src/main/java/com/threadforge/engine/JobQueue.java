package com.threadforge.engine;

import com.threadforge.model.Job;

import java.util.LinkedList;
import java.util.Queue;

public class JobQueue {

    private final Queue<Job> jobs = new LinkedList<>();

    public synchronized void addJob(Job job) {
        jobs.offer(job);
        notifyAll();
    }

    public synchronized Job getJob() throws InterruptedException {
        while (jobs.isEmpty()) {
            wait();
        }
        return jobs.poll();
    }

    public synchronized boolean isEmpty() {
        return jobs.isEmpty();
    }

    public synchronized int size() {
        return jobs.size();
    }
}
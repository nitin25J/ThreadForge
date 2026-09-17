package com.threadforge.service;

import com.threadforge.model.Job;

import java.util.Collection;

public class ReportService {

    public void printReport(Collection<Job> jobs) {
        System.out.println("\nThreadForge Report");
        System.out.println("------------------");
        System.out.println("Total Jobs: " + jobs.size());

        for (Job job : jobs) {
            System.out.println(
                    job.getJobId() + " - " +
                            job.getJobName() + " - " +
                            job.getStatus()
            );
        }

        System.out.println("------------------");
    }
}
package com.threadforge;

import com.threadforge.database.DatabaseManager;
import com.threadforge.database.JobRepository;
import com.threadforge.engine.ThreadForgeEngine;
import com.threadforge.io.ReportWriter;
import com.threadforge.model.ComputeJob;
import com.threadforge.model.DatabaseJob;
import com.threadforge.model.FileJob;
import com.threadforge.model.Job;
import com.threadforge.model.JobPriority;
import com.threadforge.model.JobStatus;
import com.threadforge.service.JobService;
import com.threadforge.service.StatisticsService;
import com.threadforge.util.Config;
import com.threadforge.util.IdGenerator;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.initialize();

        JobRepository jobRepository = new JobRepository(databaseManager);
        ThreadForgeEngine engine = new ThreadForgeEngine(3, jobRepository);
        JobService jobService = new JobService(engine, jobRepository);
        StatisticsService statistics = new StatisticsService();
        ReportWriter reportWriter = new ReportWriter();

        engine.start();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {

            System.out.println("\n===== THREADFORGE =====");
            System.out.println("1. Submit Compute Job");
            System.out.println("2. Submit File Job");
            System.out.println("3. Submit Database Job");
            System.out.println("4. View Jobs");
            System.out.println("5. View Workers");
            System.out.println("6. Statistics");
            System.out.println("7. Database History");
            System.out.println("8. Generate Report");
            System.out.println("9. Cancel Job");
            System.out.println("10. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1 -> {
                    System.out.print("Job name: ");
                    String name = scanner.nextLine();

                    System.out.print("Number: ");
                    int number = scanner.nextInt();
                    scanner.nextLine();

                    Job job = new ComputeJob(
                            IdGenerator.nextJobId(),
                            name,
                            JobPriority.MEDIUM,
                            number
                    );

                    jobService.submit(job);
                    System.out.println("Job submitted: " + job.getJobId());
                }

                case 2 -> {
                    System.out.print("Job name: ");
                    String name = scanner.nextLine();

                    System.out.print("File path: ");
                    String path = scanner.nextLine();

                    Job job = new FileJob(
                            IdGenerator.nextJobId(),
                            name,
                            JobPriority.MEDIUM,
                            path
                    );

                    jobService.submit(job);
                    System.out.println("Job submitted: " + job.getJobId());
                }

                case 3 -> {
                    System.out.print("Job name: ");
                    String name = scanner.nextLine();

                    System.out.print("SQL query: ");
                    String query = scanner.nextLine();

                    Job job = new DatabaseJob(
                            IdGenerator.nextJobId(),
                            name,
                            JobPriority.MEDIUM,
                            query
                    );

                    jobService.submit(job);
                    System.out.println("Job submitted: " + job.getJobId());
                }

                case 4 -> {
                    System.out.println("\n----- Jobs -----");

                    for (Job job : jobService.getAllJobs().values()) {
                        System.out.println(
                                job.getJobId() + " | " +
                                        job.getJobName() + " | " +
                                        job.getJobType() + " | " +
                                        job.getStatus()
                        );
                    }
                }

                case 5 -> {
                    System.out.println("\n----- Workers -----");

                    engine.getWorkerPool()
                            .getWorkers()
                            .forEach(System.out::println);
                }

                case 6 -> {
                    System.out.println("\n----- Statistics -----");

                    System.out.println("Total jobs: " +
                            statistics.totalJobs(
                                    jobService.getAllJobs().values()));

                    System.out.println("Completed: " +
                            statistics.countByStatus(
                                    jobService.getAllJobs().values(),
                                    JobStatus.COMPLETED));

                    System.out.println("Running: " +
                            statistics.countByStatus(
                                    jobService.getAllJobs().values(),
                                    JobStatus.RUNNING));

                    System.out.println("Failed: " +
                            statistics.countByStatus(
                                    jobService.getAllJobs().values(),
                                    JobStatus.FAILED));

                    System.out.println("Cancelled: " +
                            statistics.countByStatus(
                                    jobService.getAllJobs().values(),
                                    JobStatus.CANCELLED));

                    System.out.println("Average execution time: " +
                            statistics.averageExecutionTime(
                                    jobService.getAllJobs().values()) +
                            " ms");
                }

                case 7 -> {
                    System.out.println("\n----- Database History -----");

                    for (String job : jobRepository.getJobHistory()) {
                        System.out.println(job);
                    }
                }

                case 8 -> {
                    reportWriter.write(
                            Config.REPORT_FILE,
                            jobRepository.getJobHistory()
                    );
                }

                case 9 -> {
                    System.out.print("Enter job ID: ");
                    String jobId = scanner.nextLine();

                    if (jobService.cancelJob(jobId)) {
                        System.out.println("Job cancelled: " + jobId);
                    } else {
                        Job job = jobService.getJob(jobId);

                        if (job == null) {
                            System.out.println("Job not found: " + jobId);
                        } else {
                            System.out.println(
                                    "Job cannot be cancelled. Current status: " +
                                            job.getStatus()
                            );
                        }
                    }
                }

                case 10 -> {
                    running = false;
                    engine.stop();
                    System.out.println("ThreadForge stopped.");
                }

                default -> System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }
}
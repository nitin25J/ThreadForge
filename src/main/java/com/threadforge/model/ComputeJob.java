package com.threadforge.model;

public class ComputeJob extends Job {

    private final int number;

    public ComputeJob(String jobId, String jobName, JobPriority priority, int number) {
        super(jobId, jobName, priority);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public void execute() {
        long result = 1;

        for (int i = 1; i <= number; i++) {
            result *= i;
        }

        System.out.println("Computed factorial of " + number + ": " + result);
    }

    @Override
    public String getJobType() {
        return "COMPUTE";
    }
}
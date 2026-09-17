package com.threadforge.model;

import java.io.File;

public class FileJob extends Job {

    private final String filePath;

    public FileJob(String jobId, String jobName, JobPriority priority, String filePath) {
        super(jobId, jobName, priority);
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    @Override
    public void execute() {
        File file = new File(filePath);

        if (!file.exists()) {
            throw new RuntimeException("File not found: " + filePath);
        }

        System.out.println("Processing file: " + filePath);
    }

    @Override
    public String getJobType() {
        return "FILE";
    }
}
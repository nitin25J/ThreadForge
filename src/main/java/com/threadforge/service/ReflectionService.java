package com.threadforge.service;

import com.threadforge.model.Job;

public class ReflectionService {

    public void showJobInfo(Job job) {
        Class<?> type = job.getClass();

        System.out.println("Job class: " + type.getSimpleName());
        System.out.println("Job type: " + job.getJobType());

        System.out.println("Methods:");

        for (var method : type.getDeclaredMethods()) {
            System.out.println(method.getName());
        }
    }
}
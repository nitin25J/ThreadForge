package com.threadforge.util;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {

    private static final AtomicInteger counter = new AtomicInteger(1000);

    private IdGenerator() {
    }

    public static String nextJobId() {
        return "JOB-" + counter.incrementAndGet();
    }

    public static String nextWorkerId() {
        return "W-" + counter.incrementAndGet();
    }
}
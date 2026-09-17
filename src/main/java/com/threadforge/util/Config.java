package com.threadforge.util;

public class Config {

    public static final int DEFAULT_WORKERS = 3;
    public static final String DATABASE_URL = "jdbc:sqlite:threadforge.db";
    public static final String LOG_FILE = "threadforge.log";
    public static final String REPORT_FILE = "threadforge-report.txt";

    private Config() {
    }
}
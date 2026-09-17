package com.threadforge.util;

import com.threadforge.io.LogWriter;

public class LoggerUtil {

    private static final LogWriter writer =
            new LogWriter(Config.LOG_FILE);

    private LoggerUtil() {
    }

    public static void info(String message) {
        writer.write("INFO: " + message);
    }

    public static void error(String message) {
        writer.write("ERROR: " + message);
    }
}
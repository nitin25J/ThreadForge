package com.threadforge.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class LogWriter {

    private final String filePath;

    public LogWriter(String filePath) {
        this.filePath = filePath;
    }

    public void write(String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            writer.println(LocalDateTime.now() + " - " + message);
        } catch (IOException e) {
            System.out.println("Log error: " + e.getMessage());
        }
    }
}
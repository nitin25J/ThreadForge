package com.threadforge.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ReportWriter {

    public void write(String filePath, List<String> history) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {

            writer.println("ThreadForge Report");
            writer.println("------------------");
            writer.println("Total Jobs: " + history.size());

            for (String job : history) {
                writer.println(job);
            }

            System.out.println("Report saved to: " + filePath);

        } catch (IOException e) {
            System.out.println("Could not write report: " + e.getMessage());
        }
    }
}
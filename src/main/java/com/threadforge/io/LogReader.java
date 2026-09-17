package com.threadforge.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LogReader {

    public void read(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("Could not read log: " + e.getMessage());
        }
    }
}
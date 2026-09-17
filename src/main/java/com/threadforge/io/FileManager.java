package com.threadforge.io;

import java.io.File;
import java.io.IOException;

public class FileManager {

    public void createFile(String path) {
        File file = new File(path);

        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + path);
            } else {
                System.out.println("File already exists: " + path);
            }
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }

    public boolean fileExists(String path) {
        return new File(path).exists();
    }
}
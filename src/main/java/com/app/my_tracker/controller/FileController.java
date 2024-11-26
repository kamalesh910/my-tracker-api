package com.example.tracker.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FileController {

    // Endpoint to list files/folders at the root level
    @GetMapping("/list-files/{path}")
    public List<String> listFiles(@PathVariable String path) {
        // Define the directory path (use absolute or relative path)
        File directory = new File("/app/data");  // Change the path if needed

        // Log the absolute path for debugging purposes
        System.out.println("Looking for files in directory: " + directory.getAbsolutePath());

        // List to hold file/folder names
        List<String> fileList = new ArrayList<>();

        // Check if the directory exists
        if (directory.exists() && directory.isDirectory()) {
            // Log the directory contents
            System.out.println("Directory exists. Listing contents...");

            // Get all files and folders in the directory
            String[] files = directory.list();
            if (files != null) {
                // Add each file/folder to the list
                for (String fileName : files) {
                    fileList.add(fileName);
                    // Log each file found
                    System.out.println("Found file/folder: " + fileName);
                }
            }
        } else {
            System.out.println("Directory does not exist or is not a directory.");
        }

        // Return the list of files/folders
        return fileList;
    }
}

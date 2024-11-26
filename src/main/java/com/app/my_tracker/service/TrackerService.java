package com.app.my_tracker.service;

import com.app.my_tracker.model.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;


@Service
public class TrackerService {

    @Value("${tracker.data.file}")
    private String dataFilePath;

    private List<User> users;

    public TrackerService() {
        loadData();
    }

private void loadData() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(dataFilePath);

            if (file.exists()) {
                // Load from the external file
                users = objectMapper.readValue(new FileInputStream(file), new TypeReference<>() {});
                System.out.println("Data loaded from external file: " + file.getAbsolutePath());
            } else {
                // Initialize with an empty list if the file is missing
                users = new ArrayList<>();
                System.out.println("No external data file found. Starting with an empty user list.");
            }
        } catch (IOException e) {
            users = new ArrayList<>();
            e.printStackTrace();
        }
    }

    private void saveData() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(dataFilePath);

            // Ensure the parent directories exist
            file.getParentFile().mkdirs();

            // Write data to the external file
            objectMapper.writeValue(file, users);
            System.out.println("Data saved to external file: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Optional<User> validateUser(String username, String password) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst();
    }

    public List<TrackData> getTrackData(int userId) {
        return users.stream()
                .filter(user -> user.getId() == userId)
                .findFirst()
                .map(User::getTrackData)
                .orElse(new ArrayList<>());
    }

    public void addTrackData(int userId, TrackData newTrackData) {
        users.stream()
                .filter(user -> user.getId() == userId)
                .findFirst()
                .ifPresent(user -> {
                    user.addTrackData(newTrackData);
                    saveData();
                });
    }

    public boolean addNewUser(User newUser) {
        boolean usernameExists = users.stream()
                .anyMatch(user -> user.getUsername().equals(newUser.getUsername()));

        if (usernameExists) {
            return false; // Username already exists
        }

        // Generate a new unique ID for the user
        int newId = users.stream().mapToInt(User::getId).max().orElse(0) + 1;
        newUser.setId(newId);
        users.add(newUser);
        saveData();
        return true;
    }
}

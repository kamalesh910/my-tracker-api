package com.app.my_tracker.service;

import com.app.my_tracker.model.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;


@Service
public class TrackerService {

    private List<User> users;

    public TrackerService() {
        loadData();
    }

    private void loadData() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(getClass().getClassLoader().getResource("data/tracker-data.json").getFile());
     
            users = objectMapper.readValue(file, new TypeReference<>() {});
        } catch (IOException e) {
            users = new ArrayList<>();
            e.printStackTrace();
        }
    }

    private void saveData() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
             File file = new File(getClass().getClassLoader().getResource("data/tracker-data.json").getFile());
            objectMapper.writeValue(file, users);
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

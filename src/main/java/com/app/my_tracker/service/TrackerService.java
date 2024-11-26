package com.app.my_tracker.service;

import com.app.my_tracker.model.TrackData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class TrackerService {

    private final String DATA_FILE = "src/main/resources/data/tracker-data.json";
    private List<User> users;

    public TrackerService() {
        loadData();
    }

    private void loadData() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            users = mapper.readValue(new File(DATA_FILE), new TypeReference<>() {});
        } catch (IOException e) {
            users = List.of();
        }
    }

    private void saveData() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(DATA_FILE), users);
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
                .orElse(List.of());
    }

    public void addTrackData(int userId, TrackData newTrackData) {
        users.stream()
                .filter(user -> user.getId() == userId)
                .findFirst()
                .ifPresent(user -> {
                    newTrackData.setId(user.getTrackData().size() + 1);
                    user.getTrackData().add(newTrackData);
                    saveData();
                });
    }
}
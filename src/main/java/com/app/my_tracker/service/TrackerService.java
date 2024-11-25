package com.app.my_tracker.service;

import com.app.my_tracker.model.*;
import com.app.my_tracker.repository.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserRepository userRepository;

    private List<Users> users;


    // Create a new user or update an existing user
    public Users createUser(Users user) {
        return userRepository.save(user);
    }

    // Fetch user by name
    public Users getUserByName(String name) {
        return userRepository.findByName(name);
    }

    // Fetch all users
    public List<Users> getAllUsers() {
        this.users =  userRepository.findAll();
        return userRepository.findAll();
    }


    public Optional<Users> validateUser(String username, String password) {
         getAllUsers();
        return users.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst();
    }

    public List<TrackData> getTrackData(String userId) {
                 getAllUsers();
        return users.stream()
                .filter(user -> user.getId() == userId)
                .findFirst()
                .map(User::getTrackData)
                .orElse(new ArrayList<>());
    }

    public void addTrackData(String userId, TrackData newTrackData) {
      Users user = userRepository.findById(userId).get();
      user.addTrackData(newTrackData);
      userRepository.save(user);
    }

    public boolean addNewUser(Users newUser) {
        boolean usernameExists = users.stream()
                .anyMatch(user -> user.getUsername().equals(newUser.getUsername()));

        if (usernameExists) {
            return false; // Username already exists
        }

        // Generate a new unique ID for the user
        int newId = users.stream().mapToInt(User::getId).max().orElse(0) + 1;
        newUser.setId(newId);
        users.add(newUser);
        userRepository.save(newUser);
        return true;
    }
}

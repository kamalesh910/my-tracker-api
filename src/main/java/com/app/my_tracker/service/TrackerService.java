package com.app.my_tracker.service;

import com.app.my_tracker.model.*;
import com.app.my_tracker.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.query.*;
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

    
    @Autowired
    private MongoTemplate mongoTemplate;

    private List<Users> users;


    // Create a new user or update an existing user
    public Users createUser(Users user) {
        return userRepository.save(user);
    }

    // Fetch user by name
    public Users getUserByName(String name) {
        return userRepository.findByUsername(name);
    }

    // Fetch all users
    public List<Users> getAllUsers() {
        this.users =  userRepository.findAll();
        return userRepository.findAll();
    }


public Optional<Users> validateUser(String username, String password) {
    Query query = new Query();
    query.addCriteria(Criteria.where("name").is(username).and("password").is(password));
    Users user = mongoTemplate.findOne(query, Users.class);
    return Optional.ofNullable(user);
}


    public List<TrackData> getTrackData(String userId) {
    try {
        // Query to find the user by their ID
        Query query = new Query(Criteria.where("id").is(userId));
        Users user = mongoTemplate.findOne(query, Users.class);

        if (user != null) {
            // Return the user's trackData
            return user.getTrackData();
        } else {
            return Collections.emptyList();
        }
    } catch (Exception e) {
        throw new RuntimeException("Error retrieving track data", e);
    }
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
        users.add(newUser);
        userRepository.save(newUser);
        return true;
    }
}

package com.app.my_tracker.controller;

import com.app.my_tracker.model.*;
import com.app.my_tracker.service.TrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TrackerController {

    @Autowired
    private TrackerService trackerService;

    /**
     * Validates user login and returns the user ID on success.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return User ID if login is successful, or "Invalid credentials".
     */
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        return trackerService.validateUser(username, password)
                .map(user -> String.valueOf(user.getId()))
                .orElse("Invalid credentials");
    }

    /**
     * Registers a new user.
     *
     * @param user The user to register.
     * @return A success or failure message.
     */
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        boolean success = trackerService.addNewUser(user);
        return success ? "User registered successfully!" : "Registration failed: Username already exists.";
    }

    /**
     * Retrieves track data for a specific user.
     *
     * @param userId The ID of the user.
     * @return A list of track data for the user.
     */
    @GetMapping("/trackData/{userId}")
    public List<TrackData> getTrackData(@PathVariable int userId) {
        return trackerService.getTrackData(userId);
    }

    /**
     * Adds a new track data entry for a specific user.
     *
     * @param userId The ID of the user.
     * @param newTrackData The new track data to be added.
     */
    @PostMapping("/trackData/{userId}")
    public void addTrackData(@PathVariable int userId, @RequestBody TrackData newTrackData) {
        trackerService.addTrackData(userId, newTrackData);
    }
}

package com.app.my_tracker.controller;

import com.app.my_tracker.model.Users;
import com.app.my_tracker.service.TrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LoginController {

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
    public String register(@RequestBody Users user) {
        boolean success = trackerService.addNewUser(user);
        return success ? "User registered successfully!" : "Registration failed: Username already exists.";
    }
}
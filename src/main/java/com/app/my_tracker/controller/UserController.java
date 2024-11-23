package com.app.my_tracker.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static List<String> userEntries = new ArrayList<>();

    // Endpoint to fetch all user entries
    @GetMapping
    public List<String> getUserEntries() {
        return userEntries;
    }

    // Endpoint to add a user entry
    @PostMapping
    public String addUserEntry(@RequestParam String entry) {
        userEntries.add(entry);
        return "Entry added: " + entry;
    }

    // Endpoint to delete a user entry
    @DeleteMapping
    public String deleteUserEntry(@RequestParam String entry) {
        if (userEntries.remove(entry)) {
            return "Entry removed: " + entry;
        }
        return "Entry not found: " + entry;
    }
}

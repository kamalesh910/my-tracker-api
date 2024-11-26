package com.app.my_tracker.controller;

import com.app.my_tracker.model.TrackData;
import com.app.my_tracker.service.TrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private TrackerService trackerService;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        return trackerService.validateUser(username, password)
                .map(user -> String.valueOf(user.getId()))
                .orElse("Invalid credentials");
    }

    @GetMapping("/trackData/{userId}")
    public List<TrackData> getTrackData(@PathVariable int userId) {
        return trackerService.getTrackData(userId);
    }

    @PostMapping("/trackData/{userId}")
    public void addTrackData(@PathVariable int userId, @RequestBody TrackData newTrackData) {
        trackerService.addTrackData(userId, newTrackData);
    }
}
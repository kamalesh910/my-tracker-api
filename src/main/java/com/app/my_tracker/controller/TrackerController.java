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
     * Retrieves track data for a specific user.
     *
     * @param userId The ID of the user.
     * @return A list of track data for the user.
     */
    @GetMapping("/trackData/{userId}")
    public List<TrackData> getTrackData(@PathVariable String userId) {
        return trackerService.getTrackData(userId);
    }

    /**
     * Adds a new track data entry for a specific user.
     *
     * @param userId The ID of the user.
     * @param newTrackData The new track data to be added.
     */
    @PostMapping("/trackData/{userId}")
    public void addTrackData(@PathVariable String userId, @RequestBody TrackData newTrackData) {
        trackerService.addTrackData(userId, newTrackData);
    }
}

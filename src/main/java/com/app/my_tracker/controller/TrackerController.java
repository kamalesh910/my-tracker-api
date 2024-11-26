package com.app.my_tracker.controller;

import com.app.my_tracker.model.Item;
import com.app.my_tracker.service.TrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tracker")
public class TrackerController {

    @Autowired
    private TrackerService trackerService;

    // Get all items
    @GetMapping
    public List<Item> getAllItems() {
        return trackerService.getItems();
    }

    // Add a new item
    @PostMapping
    public String addItem(@RequestParam String name) {
        trackerService.addItem(name);
        return "Item added successfully";
    }
}

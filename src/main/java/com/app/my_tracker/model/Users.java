package com.app.my_tracker.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
public class Users {
    @Id
    private String id;
    private String name;
    private String username;
    private String password;
    private List<TrackData> trackData;

    public Users() {
        this.trackData = new ArrayList<>();
    }

    public Users(String id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.trackData = new ArrayList<>();
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<TrackData> getTrackData() {
        return trackData;
    }

    public void setTrackData(List<TrackData> trackData) {
        this.trackData = trackData;
    }

    public void addTrackData(TrackData data) {
        this.trackData.add(data);
    }
}

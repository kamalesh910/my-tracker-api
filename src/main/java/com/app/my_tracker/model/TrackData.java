package com.app.my_tracker.model;
import java.time.LocalDate;

public class TrackData {
    private int id;
    private String date;
    private String category;
    private int score;
    private String description;

    public TrackData() {}

    public TrackData(int id, String date, String category, int score, String description) {
        this.id = id;
        this.date = date;
        this.category = category;
        this.score = score;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

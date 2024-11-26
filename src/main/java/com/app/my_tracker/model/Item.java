package com.app.my_tracker.model;

public class Item {
    private int id;
    private String name;

    // Constructors, Getters, and Setters
    public Item() {}

    public Item(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.example;

public class Event {
    private String name;
    private String date;
    private String location;
    private String description;
    private double price;

    public Event(String name, String date, String location, String description, double price) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.description = description;
        this.price = price;
    }

    public String getName() { return name; }
    public String getDate() { return date; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
}
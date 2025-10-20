package com.example;


public class Registration {
    private Event event;
    private int participants;

    public Registration(Event event, int participants) {
        this.event = event;
        this.participants = participants;
    }

    public Event getEvent() { return event; }
    public int getParticipants() { return participants; }
    public void setParticipants(int participants) { this.participants = participants; }

    public double getTotal() {
        return participants * event.getPrice();
    }
}
package com.example;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import java.util.*;

@WebServlet("/events")
public class EventListServlet extends HttpServlet {
    private List<Event> events;

    @Override
    public void init() {
    	events = new ArrayList<>();
        events.add(new Event("HackConcordia Open House", "Oct 25, 2025 - 10 AM", "H520",
                "Meet the people behind HackConcordia!", 10));
        events.add(new Event("Lizard Lounge", "Oct 30, 2025 - 2 PM", "Rm1.210",
                "Compete in riddles, coding puzzles, and engineering challenges.", 25));
        events.add(new Event("ConuHacks IX", "Nov 10, 2025 - 9 AM", "H403",
                "Showcase your passion for technology, creativity, and problem-solving skills in this 24H hackathon.",
                40));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        out.println("<html><head><title>Event Listing</title>");
        out.println("<link rel='stylesheet' href='styles.css'></head><body>");
        out.println("<h1>Upcoming Events</h1>");
        out.println("<a href='summary' class='link'>My Registrations</a>");
        out.println("<hr>");

        out.println("<form action='register' method='post'>");
        for (Event e : events) {
            out.println("<div class='event'>");
            out.println("<h3>" + e.getName() + "</h3>");
            out.println("<p><b>Date:</b> " + e.getDate() + "<br>");
            out.println("<b>Location:</b> " + e.getLocation() + "<br>");
            out.println(e.getDescription() + "<br>");
            out.println("<b>Price per participant:</b> $" + e.getPrice() + "</p>");
            out.println("<label>Participants: </label>");
            out.println("<select name='" + e.getName() + "'>");
            for (int i = 1; i <= 5; i++)
                out.println("<option value='" + i + "'>" + i + "</option>");
            out.println("</select>");
            out.println("<button type='submit' name='event' value='" + e.getName() + "'>Register</button>");
            out.println("</div><hr>");
        }
        out.println("</form>");
        out.println("</body></html>");
    }

    public List<Event> getEvents() {
        return events;
    }
}

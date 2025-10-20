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
        events.add(new Event("Tech Talk", "Oct 25, 2025 - 10 AM", "Room A101", "Latest in AI trends.", 50));
        events.add(new Event("Workshop", "Oct 30, 2025 - 2 PM", "Room B202", "Hands-on coding workshop.", 75));
        events.add(new Event("Hackathon", "Nov 10, 2025 - 9 AM", "Room C303", "24-hour hackathon.", 100));
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

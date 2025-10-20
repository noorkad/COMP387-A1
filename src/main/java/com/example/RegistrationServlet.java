package com.example;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import java.util.*;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession();
        List<Registration> registrations = (List<Registration>) session.getAttribute("registrations");
        if (registrations == null) registrations = new ArrayList<>();

        // Load events
        EventListServlet listServlet = new EventListServlet();
        listServlet.init();
        List<Event> events = listServlet.getEvents();

        String eventName = req.getParameter("event");
        Event selected = events.stream().filter(e -> e.getName().equals(eventName)).findFirst().orElse(null);
        if (selected != null) {
            int participants = Integer.parseInt(req.getParameter(eventName));
            boolean updated = false;
            for (Registration r : registrations) {
                if (r.getEvent().getName().equals(eventName)) {
                    r.setParticipants(r.getParticipants() + participants);
                    updated = true;
                    break;
                }
            }
            if (!updated) registrations.add(new Registration(selected, participants));
        }

        session.setAttribute("registrations", registrations);
        res.sendRedirect("events");
    }
}

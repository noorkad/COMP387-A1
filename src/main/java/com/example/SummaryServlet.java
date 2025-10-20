package com.example;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;
import java.util.*;

@WebServlet("/summary")
public class SummaryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession();
        List<Registration> registrations = (List<Registration>) session.getAttribute("registrations");
        if (registrations == null)
            registrations = new ArrayList<>();

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        out.println("<html><head><title>My Registrations</title>");
        out.println("<link rel='stylesheet' href='styles.css'></head><body>");
        out.println("<h1>My Registrations</h1>");
        out.println("<form action='summary' method='post'>");
        out.println(
                "<table><tr><th>Event</th><th>Date/Time</th><th>Participants</th><th>Price</th><th>Total</th><th>Actions</th></tr>");

        for (Registration r : registrations) {
            out.println("<tr>");
            out.println("<td>" + r.getEvent().getName() + "</td>");
            out.println("<td>" + r.getEvent().getDate() + "</td>");
            out.println("<td><input type='number' name='" + r.getEvent().getName() + "' value='" + r.getParticipants()
                    + "' min='0'></td>");
            out.println("<td>$" + r.getEvent().getPrice() + "</td>");
            out.println("<td>$" + r.getTotal() + "</td>");
            out.println("<td><button name='action' value='update:" + r.getEvent().getName() + "'>Update</button>");
            out.println("<button name='action' value='cancel:" + r.getEvent().getName() + "'>Cancel</button></td>");
            out.println("</tr>");
        }

        out.println("</table>");
        out.println("<a href='events'>Continue Browsing</a>");
        out.println("<button formaction='confirm'>Confirm Registration</button>");
        out.println("</form></body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession();
        List<Registration> registrations = (List<Registration>) session.getAttribute("registrations");

        String action = req.getParameter("action");
        if (action != null && registrations != null) {
            if (action.startsWith("update:")) {
                String eventName = action.substring(7);
                int newCount = Integer.parseInt(req.getParameter(eventName));
                for (Registration r : registrations) {
                    if (r.getEvent().getName().equals(eventName)) {
                        if (newCount == 0) {
                            req.getSession().setAttribute("warning", "Participants cannot be 0!");
                        } else {
                            r.setParticipants(newCount);
                        }
                        break;
                    }
                }
            } else if (action.startsWith("cancel:")) {
                String eventName = action.substring(7);
                registrations.removeIf(r -> r.getEvent().getName().equals(eventName));
            }
        }

        session.setAttribute("registrations", registrations);
        res.sendRedirect("summary");
    }
}

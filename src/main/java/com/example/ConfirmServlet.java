package com.example;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.*;

@WebServlet("/confirm")
public class ConfirmServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Accept GET as well (either show same confirmation or redirect)
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("registrations");

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><head><title>Confirmation</title>");
        out.println("<link rel='stylesheet' href='styles.css'></head><body>");
        out.println("<h1>Registration Confirmed!</h1>");
        out.println("<a href='events'>Back to Events</a>");
        out.println("</body></html>");
    }
}

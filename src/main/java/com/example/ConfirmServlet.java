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


        out.println("<div style='text-align:center; margin-top:50px;'>");
        out.println("<h1 style='font-weight:bold; font-size:36px;'>Registration Confirmed!</h1>");
        out.println("<p style='font-size:16px;'>Thank you — your registrations have been recorded.</p>");
        out.println("<p style='font-size:16px;'>An email confirmation is <i>not</i> required for this assignment.</p>");

        out.println("<p style='font-size:16px;'><a href='events'>Back to Events</a></p>");

        
        out.println("</body></html>");
    }
}

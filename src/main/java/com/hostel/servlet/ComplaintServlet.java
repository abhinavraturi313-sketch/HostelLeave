package com.hostel.servlet;

import com.hostel.dao.ComplaintDAO;
import com.hostel.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

// Student submits a complaint (POST)
@WebServlet("/complaint")
public class ComplaintServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            return;
        }
        User user = (User) session.getAttribute("user");
        new ComplaintDAO().add(user.getId(),
                request.getParameter("category"),
                request.getParameter("description"));
        response.sendRedirect("dashboard");
    }
}

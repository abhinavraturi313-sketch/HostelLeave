package com.hostel.servlet;

import com.hostel.dao.LeaveDAO;
import com.hostel.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

// Student submits the leave form (POST)
@WebServlet("/apply")
public class ApplyLeaveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            return;
        }
        User user = (User) session.getAttribute("user");
        new LeaveDAO().apply(user.getId(),
                request.getParameter("fromDate"),
                request.getParameter("toDate"),
                request.getParameter("reason"));
        response.sendRedirect("dashboard");
    }
}

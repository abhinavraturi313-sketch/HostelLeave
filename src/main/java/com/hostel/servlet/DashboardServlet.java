package com.hostel.servlet;

import com.hostel.dao.ComplaintDAO;
import com.hostel.dao.LeaveDAO;
import com.hostel.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

// Loads leaves and complaints from the database and forwards to the right JSP based on role
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // do not create a new one
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            return;
        }
        User user = (User) session.getAttribute("user");
        LeaveDAO leaveDao = new LeaveDAO();
        ComplaintDAO complaintDao = new ComplaintDAO();

        if (user.getRole().equals("student")) {
            request.setAttribute("leaves", leaveDao.getByStudent(user.getId()));
            request.setAttribute("complaints", complaintDao.getByStudent(user.getId()));
            request.getRequestDispatcher("student.jsp").forward(request, response);
        } else {
            request.setAttribute("leaves", leaveDao.getAll());
            request.setAttribute("complaints", complaintDao.getAll());
            request.getRequestDispatcher("warden.jsp").forward(request, response);
        }
    }
}

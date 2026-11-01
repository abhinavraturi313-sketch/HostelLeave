package com.hostel.servlet;

import com.hostel.dao.UserDAO;
import com.hostel.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

// Handles login form (POST). Creates an HttpSession on success.
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new UserDAO().login(username, password);
        if (user != null) {
            HttpSession session = request.getSession(); // creates a new session
            session.setAttribute("user", user);
            response.sendRedirect("dashboard");          // sendRedirect
        } else {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("index.jsp").forward(request, response); // RequestDispatcher
        }
    }
}

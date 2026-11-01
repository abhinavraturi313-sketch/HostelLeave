package com.hostel.servlet;

import com.hostel.dao.LeaveDAO;
import com.hostel.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

// Warden approves/rejects a leave. Student can cancel a pending leave (action=cancel).
@WebServlet("/update")
public class UpdateLeaveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("index.jsp");
            return;
        }
        User user = (User) session.getAttribute("user");
        int id = Integer.parseInt(request.getParameter("id"));
        String action = request.getParameter("action");
        LeaveDAO dao = new LeaveDAO();

        if (user.getRole().equals("warden") && (action.equals("Approved") || action.equals("Rejected"))) {
            dao.updateStatus(id, action, request.getParameter("remark"));
        } else if (user.getRole().equals("student") && action.equals("cancel")) {
            dao.cancel(id, user.getId());
        }
        response.sendRedirect("dashboard");
    }
}

package com.hostel.servlet;

import com.hostel.dao.ComplaintDAO;
import com.hostel.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

// Warden resolves/rejects a complaint. Student can withdraw a pending one (action=delete).
@WebServlet("/complaintUpdate")
public class UpdateComplaintServlet extends HttpServlet {
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
        ComplaintDAO dao = new ComplaintDAO();

        if (user.getRole().equals("warden") && (action.equals("Resolved") || action.equals("Rejected"))) {
            dao.updateStatus(id, action, request.getParameter("remark"));
        } else if (user.getRole().equals("student") && action.equals("delete")) {
            dao.delete(id, user.getId());
        }
        response.sendRedirect("dashboard");
    }
}

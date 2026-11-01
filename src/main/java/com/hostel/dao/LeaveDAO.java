package com.hostel.dao;

import com.hostel.model.LeaveRequest;
import com.hostel.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// All CRUD operations for leave_requests using JDBC PreparedStatement
public class LeaveDAO {

    // CREATE: student applies for leave
    public void apply(int studentId, String from, String to, String reason) {
        String sql = "INSERT INTO leave_requests (student_id, from_date, to_date, reason) VALUES (?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.setDate(2, Date.valueOf(from));
            ps.setDate(3, Date.valueOf(to));
            ps.setString(4, reason);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ: leaves of one student
    public List<LeaveRequest> getByStudent(int studentId) {
        return fetch("SELECT l.*, u.full_name, u.room_no FROM leave_requests l "
                + "JOIN users u ON l.student_id=u.id WHERE l.student_id=? ORDER BY l.id DESC", studentId);
    }

    // READ: all leaves (for warden)
    public List<LeaveRequest> getAll() {
        return fetch("SELECT l.*, u.full_name, u.room_no FROM leave_requests l "
                + "JOIN users u ON l.student_id=u.id ORDER BY l.id DESC", -1);
    }

    // UPDATE: warden approves or rejects
    public void updateStatus(int id, String status, String remark) {
        String sql = "UPDATE leave_requests SET status=?, remark=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setString(2, remark);
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE: student cancels a leave that is still pending
    public void cancel(int id, int studentId) {
        String sql = "DELETE FROM leave_requests WHERE id=? AND student_id=? AND status='Pending'";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setInt(2, studentId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper used by both read methods
    private List<LeaveRequest> fetch(String sql, int studentId) {
        List<LeaveRequest> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            if (studentId != -1) ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new LeaveRequest(rs.getInt("id"), rs.getString("full_name"),
                        rs.getString("room_no"), rs.getString("from_date"), rs.getString("to_date"),
                        rs.getString("reason"), rs.getString("status"), rs.getString("remark"),
                        rs.getString("applied_on")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

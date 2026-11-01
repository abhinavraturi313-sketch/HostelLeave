package com.hostel.dao;

import com.hostel.model.Complaint;
import com.hostel.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// CRUD for complaints using JDBC PreparedStatement
public class ComplaintDAO {

    // CREATE
    public void add(int studentId, String category, String description) {
        String sql = "INSERT INTO complaints (student_id, category, description) VALUES (?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.setString(2, category);
            ps.setString(3, description);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ: one student's complaints
    public List<Complaint> getByStudent(int studentId) {
        return fetch("SELECT c.*, u.full_name, u.room_no FROM complaints c "
                + "JOIN users u ON c.student_id=u.id WHERE c.student_id=? ORDER BY c.id DESC", studentId);
    }

    // READ: all complaints (warden)
    public List<Complaint> getAll() {
        return fetch("SELECT c.*, u.full_name, u.room_no FROM complaints c "
                + "JOIN users u ON c.student_id=u.id ORDER BY c.id DESC", -1);
    }

    // UPDATE: warden resolves or rejects
    public void updateStatus(int id, String status, String remark) {
        String sql = "UPDATE complaints SET status=?, remark=? WHERE id=?";
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

    // DELETE: student withdraws a pending complaint
    public void delete(int id, int studentId) {
        String sql = "DELETE FROM complaints WHERE id=? AND student_id=? AND status='Pending'";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setInt(2, studentId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Complaint> fetch(String sql, int studentId) {
        List<Complaint> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            if (studentId != -1) ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Complaint(rs.getInt("id"), rs.getString("full_name"), rs.getString("room_no"),
                        rs.getString("category"), rs.getString("description"), rs.getString("status"),
                        rs.getString("remark"), rs.getString("created_on")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

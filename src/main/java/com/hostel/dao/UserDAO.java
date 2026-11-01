package com.hostel.dao;

import com.hostel.model.User;
import com.hostel.util.DBConnection;
import java.sql.*;

public class UserDAO {
    // Returns the User if username and password match, otherwise null
    public User login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username=? AND password=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("username"),
                        rs.getString("full_name"), rs.getString("role"), rs.getString("room_no"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

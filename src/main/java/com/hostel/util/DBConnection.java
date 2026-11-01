package com.hostel.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Gives a JDBC connection to MySQL. Change PASS to your MySQL password.
public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/hostel_db";
    private static final String USER = "root";
    private static final String PASS = "your_password";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // load the driver
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL driver jar not found in WEB-INF/lib", e);
        }
        return DriverManager.getConnection(URL, USER, PASS);
    }
}

package com.hostel.model;

// Simple bean that holds one row of the users table
public class User {
    private int id;
    private String username, fullName, role, roomNo;

    public User(int id, String username, String fullName, String role, String roomNo) {
        this.id = id; this.username = username; this.fullName = fullName;
        this.role = role; this.roomNo = roomNo;
    }
    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getFullName() { return fullName; }
    public String getRole() { return role; }
    public String getRoomNo() { return roomNo; }
}

package com.hostel.model;

// Simple bean that holds one complaint
public class Complaint {
    private int id;
    private String studentName, roomNo, category, description, status, remark, createdOn;

    public Complaint(int id, String studentName, String roomNo, String category, String description,
                     String status, String remark, String createdOn) {
        this.id = id; this.studentName = studentName; this.roomNo = roomNo;
        this.category = category; this.description = description;
        this.status = status; this.remark = remark; this.createdOn = createdOn;
    }
    public int getId() { return id; }
    public String getStudentName() { return studentName; }
    public String getRoomNo() { return roomNo; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public String getRemark() { return remark == null ? "-" : remark; }
    public String getCreatedOn() { return createdOn; }
}

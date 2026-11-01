package com.hostel.model;

// Simple bean that holds one leave request (with student name and room for the warden view)
public class LeaveRequest {
    private int id;
    private String studentName, roomNo, fromDate, toDate, reason, status, remark, appliedOn;

    public LeaveRequest(int id, String studentName, String roomNo, String fromDate, String toDate,
                        String reason, String status, String remark, String appliedOn) {
        this.id = id; this.studentName = studentName; this.roomNo = roomNo;
        this.fromDate = fromDate; this.toDate = toDate; this.reason = reason;
        this.status = status; this.remark = remark; this.appliedOn = appliedOn;
    }
    public int getId() { return id; }
    public String getStudentName() { return studentName; }
    public String getRoomNo() { return roomNo; }
    public String getFromDate() { return fromDate; }
    public String getToDate() { return toDate; }
    public String getReason() { return reason; }
    public String getStatus() { return status; }
    public String getRemark() { return remark == null ? "-" : remark; }
    public String getAppliedOn() { return appliedOn; }
}

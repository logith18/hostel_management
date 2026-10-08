package com.hostel.model;

import java.time.LocalDate;
import java.time.Clock;

public class Complaint {

    private String complaintId;
    private String studentId;
    private String description;
    private String status;
    private LocalDate complaintDate;

    public Complaint(String complaintId, String studentId,
                     String description) {

        this.complaintId = complaintId;
        this.studentId = studentId;
        this.description = description;
        this.status = "OPEN";
        this.complaintDate = LocalDate.now(Clock.systemDefaultZone());
    }

    public String getComplaintId() {
        return complaintId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getComplaintDate() {
        return complaintDate;
    }

    public void resolve() {
        this.status = "RESOLVED";
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "ID='" + complaintId + '\'' +
                ", Student='" + studentId + '\'' +
                ", Description='" + description + '\'' +
                ", Status='" + status + '\'' +
                ", Date=" + complaintDate +
                '}';
    }
}

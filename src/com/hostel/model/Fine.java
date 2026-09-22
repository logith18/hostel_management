package com.hostel.model;

import java.time.LocalDate;

public class Fine {

    private String fineId;
    private String studentId;
    private String reason;
    private double amount;
    private String status;
    private LocalDate fineDate;

    public Fine(String fineId, String studentId,
                String reason, double amount) {

        this.fineId = fineId;
        this.studentId = studentId;
        this.reason = reason;
        this.amount = amount;
        this.status = "PENDING";
        this.fineDate = LocalDate.now();
    }

    public String getFineId() {
        return fineId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getReason() {
        return reason;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getFineDate() {
        return fineDate;
    }

    public void markPaid() {
        this.status = "PAID";
    }

    @Override
    public String toString() {
        return "Fine{" +
                "ID='" + fineId + '\'' +
                ", Student='" + studentId + '\'' +
                ", Reason='" + reason + '\'' +
                ", Amount=" + amount +
                ", Status='" + status + '\'' +
                ", Date=" + fineDate +
                '}';
    }
}
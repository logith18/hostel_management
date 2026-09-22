package com.hostel.model;

import java.time.LocalDate;

public class Payment {

    private String paymentId;
    private String studentId;
    private double amount;
    private String paymentType;
    private String status;
    private LocalDate paymentDate;

    public Payment(String paymentId, String studentId,
                   double amount, String paymentType) {

        this.paymentId = paymentId;
        this.studentId = studentId;
        this.amount = amount;
        this.paymentType = paymentType;
        this.status = "PAID";
        this.paymentDate = LocalDate.now();
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "ID='" + paymentId + '\'' +
                ", Student='" + studentId + '\'' +
                ", Amount=" + amount +
                ", Type='" + paymentType + '\'' +
                ", Status='" + status + '\'' +
                ", Date=" + paymentDate +
                '}';
    }
}
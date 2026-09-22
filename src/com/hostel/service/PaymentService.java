package com.hostel.service;

import com.hostel.dao.PaymentDAO;
import com.hostel.model.Payment;

import java.util.List;

public class PaymentService {

    private final PaymentDAO paymentDAO;

    public PaymentService(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    public boolean makePayment(String studentId,
                               String paymentId,
                               double amount,
                               String paymentType) {

        if (studentId == null || studentId.isBlank()) {
            return false;
        }

        if (amount <= 0) {
            return false;
        }

        Payment payment =
                new Payment(
                        paymentId,
                        studentId,
                        amount,
                        paymentType
                );

        paymentDAO.addPayment(payment);

        System.out.println(
                "Payment successful: Rs. " + amount
        );

        return true;
    }

    public double getTotalPaid(String studentId) {
        return paymentDAO.getTotalPaidByStudent(studentId);
    }

    public List<Payment> getPayments(String studentId) {
        return paymentDAO.getPaymentsByStudent(studentId);
    }
}
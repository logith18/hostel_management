package com.hostel.dao;

import com.hostel.model.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    private final List<Payment> payments = new ArrayList<>();

    public void addPayment(Payment payment) {
        payments.add(payment);
    }

    public List<Payment> getPaymentsByStudent(String studentId) {

        List<Payment> result = new ArrayList<>();

        for (Payment payment : payments) {

            if (payment.getStudentId().equals(studentId)) {
                result.add(payment);
            }
        }

        return result;
    }

    public List<Payment> getAllPayments() {
        return payments;
    }

    public double getTotalPaidByStudent(String studentId) {

        double total = 0;

        for (Payment payment : payments) {

            if (payment.getStudentId().equals(studentId)
                    && payment.getStatus().equals("PAID")) {

                total += payment.getAmount();
            }
        }

        return total;
    }
}
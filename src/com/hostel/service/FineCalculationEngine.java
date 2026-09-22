package com.hostel.service;

import com.hostel.dao.FineDAO;
import com.hostel.model.Fine;

public class FineCalculationEngine {

    private final FineDAO fineDAO;

    public FineCalculationEngine(FineDAO fineDAO) {
        this.fineDAO = fineDAO;
    }

    public Fine calculateFine(String studentId,
                              String fineId,
                              String violationType,
                              int severity) {

        double amount;

        if (violationType.equalsIgnoreCase("LATE_CHECKOUT")) {

            amount = 500;

        } else if (violationType.equalsIgnoreCase("LOST_KEY")) {

            amount = 200;

        } else if (violationType.equalsIgnoreCase("ROOM_DAMAGE")) {

            if (severity <= 1) {
                amount = 1000;
            } else if (severity == 2) {
                amount = 2500;
            } else {
                amount = 5000;
            }

        } else if (violationType.equalsIgnoreCase("PENDING_PAYMENT")) {

            amount = 300;

        } else {

            amount = 500;
        }

        Fine fine =
                new Fine(fineId, studentId, violationType, amount);

        fineDAO.addFine(fine);

        System.out.println(
                "Fine generated: Rs. " + amount
        );

        return fine;
    }

    public double getOutstandingFine(String studentId) {
        return fineDAO.getOutstandingFine(studentId);
    }

    public void markFineAsPaid(String fineId) {

        Fine fine = fineDAO.findById(fineId);

        if (fine == null) {
            System.out.println("Fine not found.");
            return;
        }

        fine.markPaid();

        System.out.println("Fine marked as paid.");
    }
}
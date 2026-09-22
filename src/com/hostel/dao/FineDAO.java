package com.hostel.dao;

import com.hostel.model.Fine;

import java.util.ArrayList;
import java.util.List;

public class FineDAO {

    private final List<Fine> fines = new ArrayList<>();

    public void addFine(Fine fine) {
        fines.add(fine);
    }

    public Fine findById(String fineId) {

        for (Fine fine : fines) {

            if (fine.getFineId().equals(fineId)) {
                return fine;
            }
        }

        return null;
    }

    public List<Fine> getAllFines() {
        return fines;
    }

    public List<Fine> getFinesByStudent(String studentId) {

        List<Fine> result = new ArrayList<>();

        for (Fine fine : fines) {

            if (fine.getStudentId().equals(studentId)) {
                result.add(fine);
            }
        }

        return result;
    }

    public double getOutstandingFine(String studentId) {

        double total = 0;

        for (Fine fine : fines) {

            if (fine.getStudentId().equals(studentId)
                    && fine.getStatus().equals("PENDING")) {

                total += fine.getAmount();
            }
        }

        return total;
    }
}
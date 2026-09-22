package com.hostel.service;

import com.hostel.dao.ComplaintDAO;
import com.hostel.model.Complaint;

import java.util.List;

public class ComplaintService {

    private final ComplaintDAO complaintDAO;

    public ComplaintService(ComplaintDAO complaintDAO) {
        this.complaintDAO = complaintDAO;
    }

    public Complaint registerComplaint(String complaintId,
                                        String studentId,
                                        String description) {

        if (complaintId == null || complaintId.isBlank()) {
            return null;
        }

        if (studentId == null || studentId.isBlank()) {
            return null;
        }

        if (description == null || description.isBlank()) {
            return null;
        }

        Complaint complaint =
                new Complaint(
                        complaintId,
                        studentId,
                        description
                );

        complaintDAO.addComplaint(complaint);

        System.out.println("Complaint registered successfully.");

        return complaint;
    }

    public void resolveComplaint(String complaintId) {

        Complaint complaint =
                complaintDAO.findById(complaintId);

        if (complaint == null) {
            System.out.println("Complaint not found.");
            return;
        }

        complaint.resolve();

        System.out.println("Complaint resolved.");
    }

    public List<Complaint> getAllComplaints() {
        return complaintDAO.getAllComplaints();
    }
}
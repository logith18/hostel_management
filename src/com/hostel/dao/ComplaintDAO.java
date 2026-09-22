package com.hostel.dao;

import com.hostel.model.Complaint;

import java.util.ArrayList;
import java.util.List;

public class ComplaintDAO {

    private final List<Complaint> complaints = new ArrayList<>();

    public void addComplaint(Complaint complaint) {
        complaints.add(complaint);
    }

    public Complaint findById(String complaintId) {

        for (Complaint complaint : complaints) {

            if (complaint.getComplaintId().equals(complaintId)) {
                return complaint;
            }
        }

        return null;
    }

    public List<Complaint> getAllComplaints() {
        return complaints;
    }

    public List<Complaint> getComplaintsByStudent(String studentId) {

        List<Complaint> result = new ArrayList<>();

        for (Complaint complaint : complaints) {

            if (complaint.getStudentId().equals(studentId)) {
                result.add(complaint);
            }
        }

        return result;
    }
}
package com.hostel.service;

import com.hostel.dao.AllocationDAO;
import com.hostel.dao.RoomDAO;
import com.hostel.dao.StudentDAO;
import com.hostel.model.Allocation;
import com.hostel.model.Room;
import com.hostel.model.Student;

public class CheckoutService {

    private final StudentDAO studentDAO;
    private final RoomDAO roomDAO;
    private final AllocationDAO allocationDAO;
    private final FineCalculationEngine fineEngine;

    public CheckoutService(StudentDAO studentDAO,
                           RoomDAO roomDAO,
                           AllocationDAO allocationDAO,
                           FineCalculationEngine fineEngine) {

        this.studentDAO = studentDAO;
        this.roomDAO = roomDAO;
        this.allocationDAO = allocationDAO;
        this.fineEngine = fineEngine;
    }

    public boolean checkoutStudent(String studentId,
                                   boolean pendingPayment,
                                   boolean roomDamage,
                                   boolean lostKey,
                                   boolean lateCheckout,
                                   boolean roomClearance) {

        Student student = studentDAO.findById(studentId);

        // Condition 1: Student exists
        if (student == null) {
            System.out.println("Student not found.");
            return false;
        }

        Allocation allocation =
                allocationDAO.findByStudentId(studentId);

        if (allocation == null) {
            System.out.println("No active room allocation.");
            return false;
        }

        // Condition 2: Pending payment
        if (pendingPayment) {
            System.out.println(
                    "Checkout blocked: Pending payment."
            );
            return false;
        }

        // Condition 3: Room damage
        if (roomDamage) {
            System.out.println(
                    "Room damage detected."
            );
        }

        // Condition 4: Lost key
        if (lostKey) {
            System.out.println(
                    "Lost key detected."
            );
        }

        // Condition 5: Late checkout
        if (lateCheckout) {
            System.out.println(
                    "Late checkout detected."
            );
        }

        // Condition 6: Outstanding fine
        double outstandingFine =
                fineEngine.getOutstandingFine(studentId);

        if (outstandingFine > 0) {
            System.out.println(
                    "Outstanding fine: Rs. " + outstandingFine
            );
            return false;
        }

        // Condition 7: Room clearance
        if (!roomClearance) {
            System.out.println(
                    "Room clearance not completed."
            );
            return false;
        }

        Room room =
                roomDAO.findByRoomNumber(
                        allocation.getRoomNumber()
                );

        if (room != null) {
            room.releaseBed();
        }

        allocation.setStatus("COMPLETED");

        System.out.println(
                "Student checkout completed successfully."
        );

        return true;
    }
}
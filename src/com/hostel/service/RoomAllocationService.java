package com.hostel.service;

import com.hostel.dao.AllocationDAO;
import com.hostel.dao.RoomDAO;
import com.hostel.dao.StudentDAO;
import com.hostel.model.Allocation;
import com.hostel.model.Room;
import com.hostel.model.Student;

import java.util.List;

public class RoomAllocationService {

    private final StudentDAO studentDAO;
    private final RoomDAO roomDAO;
    private final AllocationDAO allocationDAO;

    public RoomAllocationService(StudentDAO studentDAO,
                                 RoomDAO roomDAO,
                                 AllocationDAO allocationDAO) {

        this.studentDAO = studentDAO;
        this.roomDAO = roomDAO;
        this.allocationDAO = allocationDAO;
    }

    public Allocation allocateRoom(String studentId, String roomNumber) {

        Student student = studentDAO.findById(studentId);
        Room room = roomDAO.findByRoomNumber(roomNumber);

        // Rule 1: Student must exist
        if (student == null) {
            System.out.println("Student not found.");
            return null;
        }

        // Rule 2: Student must be eligible
        if (!student.isHostelEligible()) {
            System.out.println("Student is not eligible for hostel.");
            return null;
        }

        // Rule 3: Student must not already have an allocation
        if (allocationDAO.findByStudentId(studentId) != null) {
            System.out.println("Student already has an active allocation.");
            return null;
        }

        // Rule 4: Room must exist
        if (room == null) {
            System.out.println("Room not found.");
            return null;
        }

        // Rule 5: Room must be available
        if (!room.isAvailable()) {
            System.out.println("Room is not available.");
            return null;
        }

        // Rule 6: Room must have capacity
        if (!room.hasCapacity()) {
            System.out.println("Room has no available capacity.");
            return null;
        }

        // Rule 7: Preferred room type should match
        if (!student.getPreferredRoomType()
                .equalsIgnoreCase(room.getRoomType())) {

            System.out.println("Room type does not match student preference.");
            return null;
        }

        String allocationId = "A" + (allocationDAO.getAllAllocations().size() + 1);

        Allocation allocation =
                new Allocation(allocationId, studentId, roomNumber);

        allocationDAO.addAllocation(allocation);
        room.occupyBed();

        System.out.println("Room allocated successfully.");

        return allocation;
    }

    public List<Allocation> getAllAllocations() {
        return allocationDAO.getAllAllocations();
    }

    public Allocation findStudentAllocation(String studentId) {
        return allocationDAO.findByStudentId(studentId);
    }
}
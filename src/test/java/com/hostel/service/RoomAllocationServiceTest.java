package com.hostel.service;

import com.hostel.dao.AllocationDAO;
import com.hostel.dao.RoomDAO;
import com.hostel.dao.StudentDAO;
import com.hostel.model.Allocation;
import com.hostel.model.Room;
import com.hostel.model.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RoomAllocationServiceTest {

    @Test
    void allocatesEligibleStudentToMatchingAvailableRoom() {
        StudentDAO students = new StudentDAO();
        RoomDAO rooms = new RoomDAO();
        AllocationDAO allocations = new AllocationDAO();
        students.addStudent(student("S001", true, "AC"));
        Room room = new Room("101", "AC", 2);
        rooms.addRoom(room);

        Allocation allocation = new RoomAllocationService(students, rooms, allocations)
                .allocateRoom("S001", "101");

        assertEquals("S001", allocation.getStudentId());
        assertEquals("101", allocation.getRoomNumber());
        assertEquals(1, room.getOccupiedBeds());
    }

    @Test
    void rejectsNonexistentOrIneligibleStudent() {
        StudentDAO students = new StudentDAO();
        RoomDAO rooms = new RoomDAO();
        AllocationDAO allocations = new AllocationDAO();
        rooms.addRoom(new Room("101", "AC", 2));
        students.addStudent(student("S002", false, "AC"));
        RoomAllocationService service = new RoomAllocationService(students, rooms, allocations);

        assertNull(service.allocateRoom("MISSING", "101"));
        assertNull(service.allocateRoom("S002", "101"));
    }

    @Test
    void rejectsSecondActiveAllocationMissingRoomFullRoomAndWrongType() {
        StudentDAO students = new StudentDAO();
        RoomDAO rooms = new RoomDAO();
        AllocationDAO allocations = new AllocationDAO();
        students.addStudent(student("S003", true, "AC"));
        students.addStudent(student("S004", true, "AC"));
        students.addStudent(student("S005", true, "NON-AC"));
        Room available = new Room("101", "AC", 2);
        Room full = new Room("102", "AC", 1);
        full.occupyBed();
        rooms.addRoom(available);
        rooms.addRoom(full);
        RoomAllocationService service = new RoomAllocationService(students, rooms, allocations);

        assertEquals("S003", service.allocateRoom("S003", "101").getStudentId());
        assertNull(service.allocateRoom("S003", "101"));
        assertNull(service.allocateRoom("S004", "MISSING"));
        assertNull(service.allocateRoom("S004", "102"));
        assertNull(service.allocateRoom("S005", "101"));
    }

    private Student student(String id, boolean eligible, String preferredRoomType) {
        return new Student(id, "Student " + id, "CSE", 2, eligible, "General", preferredRoomType);
    }
}

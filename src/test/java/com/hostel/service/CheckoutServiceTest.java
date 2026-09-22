package com.hostel.service;

import com.hostel.dao.AllocationDAO;
import com.hostel.dao.FineDAO;
import com.hostel.dao.RoomDAO;
import com.hostel.dao.StudentDAO;
import com.hostel.model.Allocation;
import com.hostel.model.Room;
import com.hostel.model.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CheckoutServiceTest {

    @Test
    void completesCheckoutAndReleasesOccupiedBed() {
        Fixture fixture = fixture(true);

        assertTrue(fixture.service.checkoutStudent("S001", false, false, false, false, true));
        assertEquals(0, fixture.room.getOccupiedBeds());
        assertEquals("COMPLETED", fixture.allocation.getStatus());
    }

    @Test
    void rejectsMissingAllocationPaymentRestrictionAndOutstandingFine() {
        Fixture noAllocation = fixture(false);
        assertFalse(noAllocation.service.checkoutStudent("S001", false, false, false, false, true));

        Fixture paymentRestriction = fixture(true);
        assertFalse(paymentRestriction.service.checkoutStudent("S001", true, false, false, false, true));

        Fixture withFine = fixture(true);
        withFine.fineEngine.calculateFine("S001", "F001", "LATE_CHECKOUT", 1);
        assertFalse(withFine.service.checkoutStudent("S001", false, false, false, false, true));
    }

    @Test
    void recordsCurrentBaselineBehaviorWhenAllocatedRoomIsMissing() {
        StudentDAO students = new StudentDAO();
        RoomDAO rooms = new RoomDAO();
        AllocationDAO allocations = new AllocationDAO();
        FineCalculationEngine fineEngine = new FineCalculationEngine(new FineDAO());
        students.addStudent(student());
        Allocation allocation = new Allocation("A001", "S001", "MISSING");
        allocations.addAllocation(allocation);
        CheckoutService service = new CheckoutService(students, rooms, allocations, fineEngine);

        assertTrue(service.checkoutStudent("S001", false, false, false, false, true));
        assertEquals("COMPLETED", allocation.getStatus());
    }

    private Fixture fixture(boolean includeAllocation) {
        StudentDAO students = new StudentDAO();
        RoomDAO rooms = new RoomDAO();
        AllocationDAO allocations = new AllocationDAO();
        FineCalculationEngine fineEngine = new FineCalculationEngine(new FineDAO());
        Student student = student();
        Room room = new Room("101", "AC", 1);
        students.addStudent(student);
        rooms.addRoom(room);
        Allocation allocation = new Allocation("A001", "S001", "101");
        if (includeAllocation) {
            allocations.addAllocation(allocation);
            room.occupyBed();
        }
        return new Fixture(new CheckoutService(students, rooms, allocations, fineEngine), room, allocation, fineEngine);
    }

    private Student student() {
        return new Student("S001", "Test Student", "CSE", 2, true, "General", "AC");
    }

    private record Fixture(CheckoutService service, Room room, Allocation allocation,
                           FineCalculationEngine fineEngine) { }
}

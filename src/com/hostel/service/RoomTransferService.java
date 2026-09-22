package com.hostel.service;

import com.hostel.dao.AllocationDAO;
import com.hostel.dao.RoomDAO;
import com.hostel.model.Allocation;
import com.hostel.model.Room;

public class RoomTransferService {

    private final AllocationDAO allocationDAO;
    private final RoomDAO roomDAO;

    public RoomTransferService(AllocationDAO allocationDAO,
                               RoomDAO roomDAO) {
        this.allocationDAO = allocationDAO;
        this.roomDAO = roomDAO;
    }

    public boolean transferRoom(String studentId, String newRoomNumber) {

        Allocation allocation =
                allocationDAO.findByStudentId(studentId);

        if (allocation == null) {
            System.out.println("No active allocation found.");
            return false;
        }

        Room oldRoom =
                roomDAO.findByRoomNumber(allocation.getRoomNumber());

        Room newRoom =
                roomDAO.findByRoomNumber(newRoomNumber);

        if (newRoom == null) {
            System.out.println("New room not found.");
            return false;
        }

        if (oldRoom == null) {
            System.out.println("Current room not found.");
            return false;
        }

        if (oldRoom.getRoomNumber().equals(newRoomNumber)) {
            System.out.println("Student is already in this room.");
            return false;
        }

        if (!newRoom.isAvailable()) {
            System.out.println("New room is not available.");
            return false;
        }

        if (!newRoom.hasCapacity()) {
            System.out.println("New room has no available capacity.");
            return false;
        }

        oldRoom.releaseBed();
        newRoom.occupyBed();

        allocation.setRoomNumber(newRoomNumber);

        System.out.println("Room transferred successfully.");

        return true;
    }
}
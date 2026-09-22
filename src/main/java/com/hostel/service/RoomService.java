package com.hostel.service;

import com.hostel.dao.RoomDAO;
import com.hostel.model.Room;

import java.util.List;

public class RoomService {

    private final RoomDAO roomDAO;

    public RoomService(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }

    public boolean addRoom(Room room) {

        if (room == null) {
            return false;
        }

        if (room.getRoomNumber() == null
                || room.getRoomNumber().isBlank()) {
            return false;
        }

        if (room.getCapacity() <= 0) {
            return false;
        }

        if (roomDAO.findByRoomNumber(room.getRoomNumber()) != null) {
            return false;
        }

        roomDAO.addRoom(room);
        return true;
    }

    public Room findRoom(String roomNumber) {
        return roomDAO.findByRoomNumber(roomNumber);
    }

    public List<Room> getAllRooms() {
        return roomDAO.getAllRooms();
    }

    public List<Room> getAvailableRooms() {
        return roomDAO.getAvailableRooms();
    }
}
package com.hostel.model;

public class Room {

    private String roomNumber;
    private String roomType;
    private int capacity;
    private int occupiedBeds;
    private boolean available;
    private String status;

    public Room(String roomNumber, String roomType, int capacity) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.capacity = capacity;
        this.occupiedBeds = 0;
        this.available = true;
        this.status = "AVAILABLE";
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getOccupiedBeds() {
        return occupiedBeds;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getStatus() {
        return status;
    }

    public boolean hasCapacity() {
        return occupiedBeds < capacity;
    }

    public void occupyBed() {
        if (occupiedBeds < capacity) {
            occupiedBeds++;
        }

        updateStatus();
    }

    public void releaseBed() {
        if (occupiedBeds > 0) {
            occupiedBeds--;
        }

        updateStatus();
    }

    private void updateStatus() {
        if (occupiedBeds >= capacity) {
            available = false;
            status = "FULL";
        } else {
            available = true;
            status = "AVAILABLE";
        }
    }

    @Override
    public String toString() {
        return "Room{" +
                "Room='" + roomNumber + '\'' +
                ", Type='" + roomType + '\'' +
                ", Capacity=" + capacity +
                ", Occupied=" + occupiedBeds +
                ", Available=" + available +
                ", Status='" + status + '\'' +
                '}';
    }
}
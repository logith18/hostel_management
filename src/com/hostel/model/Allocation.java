package com.hostel.model;

import java.time.LocalDate;

public class Allocation {

    private String allocationId;
    private String studentId;
    private String roomNumber;
    private LocalDate allocationDate;
    private String status;

    public Allocation(String allocationId, String studentId, String roomNumber) {
        this.allocationId = allocationId;
        this.studentId = studentId;
        this.roomNumber = roomNumber;
        this.allocationDate = LocalDate.now();
        this.status = "ACTIVE";
    }

    public String getAllocationId() {
        return allocationId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public LocalDate getAllocationDate() {
        return allocationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Allocation{" +
                "ID='" + allocationId + '\'' +
                ", Student='" + studentId + '\'' +
                ", Room='" + roomNumber + '\'' +
                ", Date=" + allocationDate +
                ", Status='" + status + '\'' +
                '}';
    }
}
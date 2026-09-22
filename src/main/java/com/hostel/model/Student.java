package com.hostel.model;

public class Student {

    private String studentId;
    private String name;
    private String department;
    private int year;
    private boolean hostelEligible;
    private String category;
    private String preferredRoomType;

    public Student(String studentId, String name, String department,
                   int year, boolean hostelEligible,
                   String category, String preferredRoomType) {

        this.studentId = studentId;
        this.name = name;
        this.department = department;
        this.year = year;
        this.hostelEligible = hostelEligible;
        this.category = category;
        this.preferredRoomType = preferredRoomType;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public int getYear() {
        return year;
    }

    public boolean isHostelEligible() {
        return hostelEligible;
    }

    public String getCategory() {
        return category;
    }

    public String getPreferredRoomType() {
        return preferredRoomType;
    }

    @Override
    public String toString() {
        return "Student{" +
                "ID='" + studentId + '\'' +
                ", Name='" + name + '\'' +
                ", Department='" + department + '\'' +
                ", Year=" + year +
                ", Eligible=" + hostelEligible +
                ", Category='" + category + '\'' +
                ", Preferred Room='" + preferredRoomType + '\'' +
                '}';
    }
}
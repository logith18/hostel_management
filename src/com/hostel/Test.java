package com.hostel;

import com.hostel.model.Student;
import com.hostel.model.Room;

public class Test {

    public static void main(String[] args) {

        Student student = new Student(
                "S001",
                "Arun",
                "Software Engineering",
                4,
                true,
                "General",
                "AC"
        );

        Room room = new Room("101", "AC", 3);

        System.out.println("===== TESTING MODEL =====");
        System.out.println(student);
        System.out.println(room);

        room.occupyBed();

        System.out.println("===== AFTER BED ALLOCATION =====");
        System.out.println(room);
    }
}
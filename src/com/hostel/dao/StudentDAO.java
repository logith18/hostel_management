package com.hostel.dao;

import com.hostel.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private final List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public Student findById(String studentId) {

        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }

        return null;
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public boolean exists(String studentId) {
        return findById(studentId) != null;
    }
}
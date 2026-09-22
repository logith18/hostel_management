package com.hostel.service;

import com.hostel.dao.StudentDAO;
import com.hostel.model.Student;

import java.util.List;

public class StudentService {

    private final StudentDAO studentDAO;

    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public boolean registerStudent(Student student) {

        if (student == null) {
            return false;
        }

        if (student.getStudentId() == null
                || student.getStudentId().isBlank()) {
            return false;
        }

        if (studentDAO.exists(student.getStudentId())) {
            return false;
        }

        studentDAO.addStudent(student);
        return true;
    }

    public Student findStudent(String studentId) {
        return studentDAO.findById(studentId);
    }

    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }
}
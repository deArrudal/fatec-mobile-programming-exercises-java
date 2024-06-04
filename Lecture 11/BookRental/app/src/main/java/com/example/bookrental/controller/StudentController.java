package com.example.bookrental.controller;

import com.example.bookrental.model.Student;
import com.example.bookrental.persistence.StudentDAO;

import java.sql.SQLException;
import java.util.List;

public class StudentController implements IController<Student> {
    private final StudentDAO studentDAO;

    public StudentController(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public void registerEntry(Student student) throws SQLException {
        if (studentDAO.open() == null) {
            studentDAO.open();
        }

        studentDAO.registerEntry(student);

        studentDAO.close();
    }

    @Override
    public void updateEntry(Student student) throws SQLException {
        if (studentDAO.open() == null) {
            studentDAO.open();
        }

        studentDAO.updateEntry(student);

        studentDAO.close();
    }

    @Override
    public void removeEntry(Student student) throws SQLException {
        if (studentDAO.open() == null) {
            studentDAO.open();
        }

        studentDAO.removeEntry(student);

        studentDAO.close();
    }

    @Override
    public Student searchEntry(Student student) throws SQLException {
        if (studentDAO.open() == null) {
            studentDAO.open();
        }

        return studentDAO.searchEntry(student);

    }

    @Override
    public List<Student> listEntry() throws SQLException {
        if (studentDAO.open() == null) {
            studentDAO.open();
        }

        return studentDAO.listEntry();
    }
}

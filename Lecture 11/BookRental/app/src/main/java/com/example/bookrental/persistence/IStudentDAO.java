package com.example.bookrental.persistence;

import java.sql.SQLException;

public interface IStudentDAO {
    public StudentDAO open() throws SQLException;

    public void close();
}

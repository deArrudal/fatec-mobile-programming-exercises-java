package com.example.bookrental.persistence;

import java.sql.SQLException;

public interface IBookDAO {
    public BookDAO open() throws SQLException;

    public void close();
}

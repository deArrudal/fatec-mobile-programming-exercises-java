package com.example.bookrental.persistence;

import java.sql.SQLException;

public interface IRentalDAO {
    public RentalDAO open() throws SQLException;

    public void close();
}

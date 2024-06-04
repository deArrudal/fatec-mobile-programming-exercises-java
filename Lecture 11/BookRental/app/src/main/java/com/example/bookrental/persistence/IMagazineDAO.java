package com.example.bookrental.persistence;

import java.sql.SQLException;

public interface IMagazineDAO {
    public MagazineDAO open() throws SQLException;

    public void close();
}

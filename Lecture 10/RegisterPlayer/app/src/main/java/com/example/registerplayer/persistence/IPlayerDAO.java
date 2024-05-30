package com.example.registerplayer.persistence;

import java.sql.SQLException;

public interface IPlayerDAO {
    public PlayerDAO open() throws SQLException;
    public void close();
}

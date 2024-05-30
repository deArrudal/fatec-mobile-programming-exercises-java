package com.example.registerplayer.persistence;

import java.sql.SQLException;

public interface ITeamDAO {
    public TeamDAO open() throws SQLException;
    public void close();
}

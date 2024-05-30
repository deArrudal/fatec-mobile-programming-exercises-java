package com.example.registerplayer.controller;

import java.sql.SQLException;
import java.util.List;

public interface IController<T> {
    public void registerEntry(T t) throws SQLException;

    public void updateEntry(T t) throws SQLException;

    public void removeEntry(T t) throws SQLException;

    public T searchEntry(T t) throws SQLException;

    public List<T> listEntry() throws SQLException;
}

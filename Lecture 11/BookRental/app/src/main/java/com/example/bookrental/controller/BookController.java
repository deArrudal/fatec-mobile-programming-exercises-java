package com.example.bookrental.controller;

import com.example.bookrental.model.Book;
import com.example.bookrental.persistence.BookDAO;

import java.sql.SQLException;
import java.util.List;

public class BookController implements IController<Book> {
    private final BookDAO bookDAO;

    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public void registerEntry(Book book) throws SQLException {
        if (bookDAO.open() == null) {
            bookDAO.open();
        }

        bookDAO.registerEntry(book);

        bookDAO.close();
    }

    @Override
    public void updateEntry(Book book) throws SQLException {
        if (bookDAO.open() == null) {
            bookDAO.open();
        }

        bookDAO.updateEntry(book);

        bookDAO.close();
    }

    @Override
    public void removeEntry(Book book) throws SQLException {
        if (bookDAO.open() == null) {
            bookDAO.open();
        }

        bookDAO.removeEntry(book);

        bookDAO.close();
    }

    @Override
    public Book searchEntry(Book book) throws SQLException {
        if (bookDAO.open() == null) {
            bookDAO.open();
        }

        return bookDAO.searchEntry(book);

    }

    @Override
    public List<Book> listEntry() throws SQLException {
        if (bookDAO.open() == null) {
            bookDAO.open();
        }

        return bookDAO.listEntry();
    }
}

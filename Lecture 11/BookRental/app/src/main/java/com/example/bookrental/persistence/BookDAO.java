package com.example.bookrental.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bookrental.model.Book;
import com.example.bookrental.model.Magazine;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO implements IBookDAO, ICRUDDAO<Book> {
    private final Context context;
    private GenericDAO genericDAO;
    private SQLiteDatabase db;

    public BookDAO(Context context) {
        this.context = context;
    }

    @Override
    public BookDAO open() throws SQLException {
        genericDAO = new GenericDAO(context);
        db = genericDAO.getWritableDatabase();
        db.execSQL("PRAGMA foreign_keys=ON;");
        //db.setForeignKeyConstraintsEnabled(true);

        return this;
    }

    @Override
    public void close() {
        genericDAO.close();
    }

    private static ContentValues getContentValues(Book book, boolean isSuper) {
        ContentValues contentValues = new ContentValues();
        if (isSuper) {
            contentValues.put("id", book.getExemplarId());
            contentValues.put("name", book.getExemplarName());
            contentValues.put("pages", book.getExemplarPages());
        } else {
            contentValues.put("isbn", book.getBookISBN());
            contentValues.put("edition", book.getBookEdition());
            contentValues.put("exemplarId", book.getExemplarId());
        }

        return contentValues;
    }

    @Override
    public void registerEntry(Book book) throws SQLException {
        db.insert("exemplar", null, getContentValues(book, true));
        db.insert("book", null, getContentValues(book, false));
    }

    @Override
    public void updateEntry(Book book) throws SQLException {
        db.update("exemplar", getContentValues(book, true),
                "id = " + book.getExemplarId(), null);
        db.update("book", getContentValues(book, false),
                "exemplarId = " + book.getExemplarId(), null);
    }

    @Override
    public void removeEntry(Book book) throws SQLException {
        db.delete("book", "exemplarId = " + book.getExemplarId(), null);
        db.delete("exemplar", "id = " + book.getExemplarId(), null);
    }

    @SuppressLint("Range")
    @Override
    public Book searchEntry(Book book) throws SQLException {
        String querySQL = "SELECT " +
                "exemplar.id , exemplar.name, exemplar.pages, book.isbn, book.edition " +
                "FROM exemplar, book " +
                "WHERE exemplar.id = book.exemplarId " +
                "AND exemplar.id = " + book.getExemplarId();

        Cursor cursor = db.rawQuery(querySQL, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        if (!cursor.isAfterLast()) {
            book.setExemplarId(cursor.getInt(cursor.getColumnIndex("id")));
            book.setExemplarName(cursor.getString(cursor.getColumnIndex("name")));
            book.setExemplarPages(cursor.getInt(cursor.getColumnIndex("pages")));
            book.setBookISBN(cursor.getString(cursor.getColumnIndex("isbn")));
            book.setBookEdition(cursor.getInt(cursor.getColumnIndex("edition")));
        }

        cursor.close();

        return book;
    }

    @SuppressLint("Range")
    @Override
    public List<Book> listEntry() throws SQLException {
        List<Book> books = new ArrayList<>();

        String querySQL = "SELECT " +
                "exemplar.id, exemplar.name, exemplar.pages, book.isbn, book.edition " +
                "FROM exemplar, book " +
                "WHERE exemplar.id = book.exemplarId ";

        Cursor cursor = db.rawQuery(querySQL, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        while (!cursor.isAfterLast()) {
            Book book = new Book(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getInt(cursor.getColumnIndex("pages")),
                    cursor.getString(cursor.getColumnIndex("isbn")),
                    cursor.getInt(cursor.getColumnIndex("edition"))
            );

            books.add(book);

            cursor.moveToNext();
        }

        cursor.close();

        return books;
    }
}

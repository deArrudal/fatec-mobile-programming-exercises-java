package com.example.bookrental.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bookrental.model.Book;
import com.example.bookrental.model.Magazine;
import com.example.bookrental.model.Rental;
import com.example.bookrental.model.Student;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentalDAO implements IRentalDAO, ICRUDDAO<Rental> {
    private final Context context;
    private GenericDAO genericDAO;
    private SQLiteDatabase db;

    public RentalDAO(Context context) {
        this.context = context;
    }

    @Override
    public RentalDAO open() throws SQLException {
        genericDAO = new GenericDAO(context);
        db = genericDAO.getWritableDatabase();
        db.setForeignKeyConstraintsEnabled(true);

        return this;
    }

    @Override
    public void close() {
        genericDAO.close();
    }

    private static ContentValues getContentValues(Rental rental) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("dateWithdraw", rental.getRentalDateWithdraw().toString());
        contentValues.put("dateReturn", rental.getRentalDateReturn().toString());
        contentValues.put("studentId", rental.getRentalStudent().getStudentId());
        contentValues.put("exemplarId", rental.getRentalExemplar().getExemplarId());

        return contentValues;
    }

    @Override
    public void registerEntry(Rental rental) throws SQLException {
        db.insert("rental", null, getContentValues(rental));
    }

    @Override
    public void updateEntry(Rental rental) throws SQLException {
        String whereQuery = "dateWithdraw = ? AND studentID = ? AND exemplarID = ?";
        String[] whereArgs = new String[]{
                rental.getRentalDateWithdraw().toString(),
                String.valueOf(rental.getRentalStudent().getStudentId()),
                String.valueOf(rental.getRentalExemplar().getExemplarId())};

        db.update("rental", getContentValues(rental), whereQuery, whereArgs);
    }

    @Override
    public void removeEntry(Rental rental) throws SQLException {
        String whereQuery = "dateWithdraw = ? AND studentID = ? AND exemplarID = ?";
        String[] whereArgs = new String[]{
                rental.getRentalDateWithdraw().toString(),
                String.valueOf(rental.getRentalStudent().getStudentId()),
                String.valueOf(rental.getRentalExemplar().getExemplarId())};

        db.delete("rental", whereQuery, whereArgs);
    }

    @SuppressLint("Range")
    @Override
    public Rental searchEntry(Rental rental) throws SQLException {
        String querySQL = "SELECT " +
                "rental.dateWithdraw AS rDateWithdraw, rental.dateReturn AS rDateReturn, " +
                "student.id AS sId, student.name AS sName, student.email AS sEmail, " +
                "exemplar.id AS eId, exemplar.name AS eName, exemplar.pages AS ePages, " +
                "book.isbn AS bIsbn, book.edition AS bEdition, " +
                "magazine.issn AS mIssn " +
                "FROM rental " +
                "INNER JOIN student ON rental.studentId = student.id " +
                "INNER JOIN exemplar ON rental.exemplarId = exemplar.id " +
                "LEFT JOIN magazine ON exemplar.id = magazine.exemplarId " +
                "LEFT JOIN book ON exemplar.id = book.exemplarId " +
                "WHERE rental.dateWithdraw = " + rental.getRentalDateWithdraw().toString() + " " +
                "AND rental.studentId = " + rental.getRentalStudent().getStudentId() + " " +
                "AND rental.exemplarId = " + rental.getRentalExemplar().getExemplarId();

        Cursor cursor = db.rawQuery(querySQL, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        if (!cursor.isAfterLast()) {
            Student student = new Student(
                    cursor.getInt(cursor.getColumnIndex("sId")),
                    cursor.getString(cursor.getColumnIndex("sName")),
                    cursor.getString(cursor.getColumnIndex("sEmail"))
            );
            rental.setRentalStudent(student);

            if (!cursor.isNull(cursor.getColumnIndex("bIsbn"))) {
                Book book = new Book(
                        cursor.getInt(cursor.getColumnIndex("eId")),
                        cursor.getString(cursor.getColumnIndex("eName")),
                        cursor.getInt(cursor.getColumnIndex("ePages")),
                        cursor.getString(cursor.getColumnIndex("bIsbn")),
                        cursor.getInt(cursor.getColumnIndex("bEdition"))
                );
                rental.setRentalExemplar(book);

            } else {
                Magazine magazine = new Magazine(
                        cursor.getInt(cursor.getColumnIndex("eId")),
                        cursor.getString(cursor.getColumnIndex("eName")),
                        cursor.getInt(cursor.getColumnIndex("ePages")),
                        cursor.getString(cursor.getColumnIndex("mIssn"))
                );
                rental.setRentalExemplar(magazine);

            }

            rental.setRentalDateWithdraw(LocalDate.parse(
                    cursor.getString(cursor.getColumnIndex("rDateWithdraw"))));
            rental.setRentalDateReturn(LocalDate.parse(
                    cursor.getString(cursor.getColumnIndex("rDateReturn"))));

        }

        cursor.close();

        return rental;
    }

    @SuppressLint("Range")
    @Override
    public List<Rental> listEntry() throws SQLException {
        List<Rental> rentals = new ArrayList<>();

        String querySQL = "SELECT " +
                "rental.dateWithdraw AS rDateWithdraw, rental.dateReturn AS rDateReturn, " +
                "student.id AS sId, student.name AS sName, student.email AS sEmail, " +
                "exemplar.id AS eId, exemplar.name AS eName, exemplar.pages AS ePages, " +
                "book.isbn AS bIsbn, book.edition AS bEdition, " +
                "magazine.issn AS mIssn " +
                "FROM rental " +
                "INNER JOIN student ON rental.studentId = student.id " +
                "INNER JOIN exemplar ON rental.exemplarId = exemplar.id " +
                "LEFT JOIN magazine ON exemplar.id = magazine.exemplarId " +
                "LEFT JOIN book ON exemplar.id = book.exemplarId ";

        Cursor cursor = db.rawQuery(querySQL, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        while (!cursor.isAfterLast()) {
            Rental rental = new Rental();

            Student student = new Student(
                    cursor.getInt(cursor.getColumnIndex("sId")),
                    cursor.getString(cursor.getColumnIndex("sName")),
                    cursor.getString(cursor.getColumnIndex("sEmail"))
            );
            rental.setRentalStudent(student);

            if (!cursor.isNull(cursor.getColumnIndex("bIsbn"))) {
                Book book = new Book(
                        cursor.getInt(cursor.getColumnIndex("eId")),
                        cursor.getString(cursor.getColumnIndex("eName")),
                        cursor.getInt(cursor.getColumnIndex("ePages")),
                        cursor.getString(cursor.getColumnIndex("bIsbn")),
                        cursor.getInt(cursor.getColumnIndex("bEdition"))
                );
                rental.setRentalExemplar(book);

            } else {
                Magazine magazine = new Magazine(
                        cursor.getInt(cursor.getColumnIndex("eId")),
                        cursor.getString(cursor.getColumnIndex("eName")),
                        cursor.getInt(cursor.getColumnIndex("ePages")),
                        cursor.getString(cursor.getColumnIndex("mIssn"))
                );
                rental.setRentalExemplar(magazine);

            }

            rental.setRentalDateWithdraw(LocalDate.parse(
                    cursor.getString(cursor.getColumnIndex("rDateWithdraw"))));
            rental.setRentalDateReturn(LocalDate.parse(
                    cursor.getString(cursor.getColumnIndex("rDateReturn"))));

            rentals.add(rental);

            cursor.moveToNext();
        }

        cursor.close();

        return rentals;
    }
}

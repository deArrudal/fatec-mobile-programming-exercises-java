package com.example.bookrental.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bookrental.model.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements IStudentDAO, ICRUDDAO<Student> {
    private final Context context;
    private GenericDAO genericDAO;
    private SQLiteDatabase db;

    public StudentDAO(Context context) {
        this.context = context;
    }

    @Override
    public StudentDAO open() throws SQLException {
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

    private static ContentValues getContentValues(Student student) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", student.getStudentId());
        contentValues.put("name", student.getStudentName());
        contentValues.put("email", student.getStudentEmail());

        return contentValues;
    }

    @Override
    public void registerEntry(Student student) throws SQLException {
        db.insert("student", null, getContentValues(student));
    }

    @Override
    public void updateEntry(Student student) throws SQLException {
        db.update("student", getContentValues(student),
                "id = " + student.getStudentId(), null);
    }

    @Override
    public void removeEntry(Student student) throws SQLException {
        db.delete("student", "id = " + student.getStudentId(), null);
    }

    @SuppressLint("Range")
    @Override
    public Student searchEntry(Student student) throws SQLException {
        String querySQL = "SELECT " +
                "id, name, email " +
                "FROM student " +
                "WHERE id = " + student.getStudentId();

        Cursor cursor = db.rawQuery(querySQL, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        if (!cursor.isAfterLast()) {
            student.setStudentId(cursor.getInt(cursor.getColumnIndex("id")));
            student.setStudentName(cursor.getString(cursor.getColumnIndex("name")));
            student.setStudentEmail(cursor.getString(cursor.getColumnIndex("email")));
        }

        cursor.close();

        return student;
    }

    @SuppressLint("Range")
    @Override
    public List<Student> listEntry() throws SQLException {
        List<Student> students = new ArrayList<>();

        String querySQL = "SELECT " +
                "id, name, email " +
                "FROM student";

        Cursor cursor = db.rawQuery(querySQL, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        while (!cursor.isAfterLast()) {
            Student student = new Student(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("email"))
            );

            students.add(student);

            cursor.moveToNext();
        }

        cursor.close();

        return students;
    }
}

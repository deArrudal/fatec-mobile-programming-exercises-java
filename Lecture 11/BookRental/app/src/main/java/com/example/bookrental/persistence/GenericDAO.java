package com.example.bookrental.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GenericDAO extends SQLiteOpenHelper {
    private static final String DATABASE = "BOOKRENTAL.DB";
    private static final int DATABASE_VER = 1;
    private static final String CREATE_TABLE_STUDENT =
            "CREATE TABLE student( " +
                    "id INTEGER(10) NOT NULL PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "email VARCHAR(50) NOT NULL);";
    private static final String CREATE_TABLE_EXEMPLAR =
            "CREATE TABLE exemplar( " +
                    "id INTEGER(10) NOT NULL PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, " +
                    "pages INTEGER(10) NOT NULL);";
    private static final String CREATE_TABLE_BOOK =
            "CREATE TABLE book( " +
                    "isbn CHAR(13) NOT NULL, " +
                    "edition INTEGER(10) NOT NULL, " +
                    "exemplarId INTEGER(10), " +
                    "FOREIGN KEY (exemplarId) REFERENCES exemplar (id) ON DELETE CASCADE);";
    private static final String CREATE_TABLE_MAGAZINE =
            "CREATE TABLE magazine( " +
                    "issn CHAR(8) NOT NULL, " +
                    "exemplarId INTEGER(10), " +
                    "FOREIGN KEY (exemplarId) REFERENCES exemplar (id) ON DELETE CASCADE);";
    private static final String CREATE_TABLE_RENTAL =
            "CREATE TABLE rental( " +
                    "dateWithdraw VARCHAR(10) NOT NULL, " +
                    "dateReturn VARCHAR(10) NOT NULL, " +
                    "studentId INTEGER(10),  " +
                    "exemplarId INTEGER(10), " +
                    "FOREIGN KEY (studentId) REFERENCES student (id) ON DELETE CASCADE, " +
                    "FOREIGN KEY (exemplarId) REFERENCES exemplar (id) ON DELETE CASCADE, " +
                    "PRIMARY KEY (dateWithdraw, studentId, exemplarId));";

    public GenericDAO(Context context) {
        super(context, DATABASE, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STUDENT);
        db.execSQL(CREATE_TABLE_EXEMPLAR);
        db.execSQL(CREATE_TABLE_BOOK);
        db.execSQL(CREATE_TABLE_MAGAZINE);
        db.execSQL(CREATE_TABLE_RENTAL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS rental");
            db.execSQL("DROP TABLE IF EXISTS magazine");
            db.execSQL("DROP TABLE IF EXISTS book");
            db.execSQL("DROP TABLE IF EXISTS exemplar");
            db.execSQL("DROP TABLE IF EXISTS student");

            onCreate(db);
        }
    }
}

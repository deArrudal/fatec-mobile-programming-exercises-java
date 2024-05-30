package com.example.registerplayer.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GenericDAO extends SQLiteOpenHelper {
    private static final String DATABASE = "SPORT.DB";
    private static final int DATABASE_VER = 1;
    private static final String CREATE_TABLE_TEAM =
            "CREATE TABLE team( " +
                    "id INTEGER(10) NOT NULL PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, " +
                    "cityName VARCHAR(50) NOT NULL);";
    private static final String CREATE_TABLE_PLAYER =
            "CREATE TABLE player( " +
                    "id INTEGER(10) NOT NULL PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, " +
                    "birthDate VARCHAR(50) NOT NULL, " +
                    "height DECIMAL(3,2) NOT NULL, " +
                    "weight DECIMAL(5,2) NOT NULL, " +
                    "teamId INTEGER(10), FOREIGN KEY (teamId) REFERENCES team (id));";

    public GenericDAO(Context context) {
        super(context, DATABASE, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TEAM);
        db.execSQL(CREATE_TABLE_PLAYER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS player");
            db.execSQL("DROP TABLE IF EXISTS team");

            onCreate(db);
        }
    }
}

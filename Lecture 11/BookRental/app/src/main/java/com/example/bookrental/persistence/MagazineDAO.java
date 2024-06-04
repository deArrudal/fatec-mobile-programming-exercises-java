package com.example.bookrental.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bookrental.model.Magazine;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MagazineDAO implements IMagazineDAO, ICRUDDAO<Magazine> {
    private final Context context;
    private GenericDAO genericDAO;
    private SQLiteDatabase db;

    public MagazineDAO(Context context) {
        this.context = context;
    }

    @Override
    public MagazineDAO open() throws SQLException {
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

    private static ContentValues getContentValues(Magazine magazine, boolean isSuper) {
        ContentValues contentValues = new ContentValues();
        if (isSuper) {
            contentValues.put("id", magazine.getExemplarId());
            contentValues.put("name", magazine.getExemplarName());
            contentValues.put("pages", magazine.getExemplarPages());
        } else {
            contentValues.put("issn", magazine.getMagazineISSN());
            contentValues.put("exemplarId", magazine.getExemplarId());
        }

        return contentValues;
    }

    @Override
    public void registerEntry(Magazine magazine) throws SQLException {
        db.insert("exemplar", null, getContentValues(magazine, true));
        db.insert("magazine", null, getContentValues(magazine, false));
    }

    @Override
    public void updateEntry(Magazine magazine) throws SQLException {
        db.update("exemplar", getContentValues(magazine, true),
                "id = " + magazine.getExemplarId(), null);
        db.update("magazine", getContentValues(magazine, false),
                "exemplarId = " + magazine.getExemplarId(), null);
    }

    @Override
    public void removeEntry(Magazine magazine) throws SQLException {
        db.delete("magazine", "exemplarId = " + magazine.getExemplarId(), null);
        db.delete("exemplar", "id = " + magazine.getExemplarId(), null);
    }

    @SuppressLint("Range")
    @Override
    public Magazine searchEntry(Magazine magazine) throws SQLException {
        String querySQL = "SELECT " +
                "exemplar.id, exemplar.name, exemplar.pages, magazine.issn " +
                "FROM exemplar, magazine " +
                "WHERE exemplar.id = magazine.exemplarId " +
                "AND exemplar.id = " + magazine.getExemplarId();

        Cursor cursor = db.rawQuery(querySQL, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        if (!cursor.isAfterLast()) {
            magazine.setExemplarId(cursor.getInt(cursor.getColumnIndex("id")));
            magazine.setExemplarName(cursor.getString(cursor.getColumnIndex("name")));
            magazine.setExemplarPages(cursor.getInt(cursor.getColumnIndex("pages")));
            magazine.setMagazineISSN(cursor.getString(cursor.getColumnIndex("issn")));
        }

        cursor.close();

        return magazine;
    }

    @SuppressLint("Range")
    @Override
    public List<Magazine> listEntry() throws SQLException {
        List<Magazine> magazines = new ArrayList<>();

        String querySQL = "SELECT " +
                "exemplar.id , exemplar.name, exemplar.pages, magazine.issn " +
                "FROM exemplar, magazine " +
                "WHERE exemplar.id = magazine.exemplarId ";

        Cursor cursor = db.rawQuery(querySQL, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        while (!cursor.isAfterLast()) {
            Magazine magazine = new Magazine(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getInt(cursor.getColumnIndex("pages")),
                    cursor.getString(cursor.getColumnIndex("issn"))
            );

            magazines.add(magazine);

            cursor.moveToNext();
        }

        cursor.close();

        return magazines;
    }
}

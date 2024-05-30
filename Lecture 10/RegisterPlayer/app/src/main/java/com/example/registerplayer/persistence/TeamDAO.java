package com.example.registerplayer.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.registerplayer.model.Team;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO implements ITeamDAO, ICRUDDao<Team> {
    private final Context context;
    private GenericDAO genericDAO;
    private SQLiteDatabase db;

    public TeamDAO(Context context) {
        this.context = context;
    }

    @Override
    public TeamDAO open() throws SQLException {
        genericDAO = new GenericDAO(context);
        db = genericDAO.getWritableDatabase();

        return this;
    }

    @Override
    public void close() {
        genericDAO.close();
    }

    private static ContentValues getContentValues(Team team) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", team.getTeamID());
        contentValues.put("name", team.getTeamName());
        contentValues.put("cityName", team.getTeamCity());

        return contentValues;
    }

    @Override
    public void registerEntry(Team team) throws SQLException {
        db.insert("team", null, getContentValues(team));
    }

    @Override
    public void updateEntry(Team team) throws SQLException {
        db.update("team", getContentValues(team),
                "id = " + team.getTeamID(), null);
    }

    @Override
    public void removeEntry(Team team) throws SQLException {
        db.delete("team", "id = " + team.getTeamID(), null);
    }

    @SuppressLint("Range")
    @Override
    public Team searchEntry(Team team) throws SQLException {
        String querySQL = "SELECT id, name, cityName FROM team WHERE id = " + team.getTeamID();

        Cursor cursor = db.rawQuery(querySQL, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        if (!cursor.isAfterLast()) {
            team.setTeamID(cursor.getInt(cursor.getColumnIndex("id")));
            team.setTeamName(cursor.getString(cursor.getColumnIndex("name")));
            team.setTeamCity(cursor.getString(cursor.getColumnIndex("cityName")));
        }

        cursor.close();

        return team;
    }

    @SuppressLint("Range")
    @Override
    public List<Team> listEntry() throws SQLException {
        List<Team> teams = new ArrayList<>();

        String querySQL = "SELECT id, name, cityName FROM team";

        Cursor cursor = db.rawQuery(querySQL, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        while (!cursor.isAfterLast()) {
            Team team = new Team(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("cityName"))
            );

            teams.add(team);

            cursor.moveToNext();
        }

        cursor.close();

        return teams;
    }
}

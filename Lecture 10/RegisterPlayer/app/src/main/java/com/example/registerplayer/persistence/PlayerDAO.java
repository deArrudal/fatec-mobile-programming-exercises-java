package com.example.registerplayer.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.registerplayer.model.Player;
import com.example.registerplayer.model.Team;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO implements IPlayerDAO, ICRUDDao<Player> {
    private final Context context;
    private GenericDAO genericDAO;
    private SQLiteDatabase db;

    public PlayerDAO(Context context) {
        this.context = context;
    }

    @Override
    public PlayerDAO open() throws SQLException {
        genericDAO = new GenericDAO(context);
        db = genericDAO.getWritableDatabase();

        return this;
    }

    @Override
    public void close() {
        genericDAO.close();
    }

    private static ContentValues getContentValues(Player player) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", player.getPlayerID());
        contentValues.put("name", player.getPlayerName());
        contentValues.put("birthDate", player.getPlayerBirthDate().toString());
        contentValues.put("height", player.getPlayerHeight());
        contentValues.put("weight", player.getPlayerWeight());
        contentValues.put("teamId", player.getPlayerTeam().getTeamID());

        return contentValues;
    }

    @Override
    public void registerEntry(Player player) throws SQLException {
        db.insert("player", null, getContentValues(player));
    }

    @Override
    public void updateEntry(Player player) throws SQLException {
        db.update("player", getContentValues(player),
                "id = " + player.getPlayerID(), null);
    }

    @Override
    public void removeEntry(Player player) throws SQLException {
        db.delete("player", "id = " + player.getPlayerID(), null);
    }

    @SuppressLint("Range")
    @Override
    public Player searchEntry(Player player) throws SQLException {
        String querySQL = "SELECT "
                + "player.id, player.name, player.birthDate, player.height, player.weight, "
                + "team.id AS team_id, team.name AS team_name, team.cityName AS team_cityName "
                + "FROM player, team "
                + "WHERE player.teamId = team.id "
                + "AND player.id = " + player.getPlayerID();

        Cursor cursor = db.rawQuery(querySQL, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        if (!cursor.isAfterLast()) {
            Team team = new Team(
                    cursor.getInt(cursor.getColumnIndex("team_id")),
                    cursor.getString(cursor.getColumnIndex("team_name")),
                    cursor.getString(cursor.getColumnIndex("team_cityName"))
            );

            player.setPlayerID(cursor.getInt(cursor.getColumnIndex("id")));
            player.setPlayerName(cursor.getString(cursor.getColumnIndex("name")));
            player.setPlayerBirthDate(LocalDate.parse(
                    cursor.getString(cursor.getColumnIndex("birthDate"))));
            player.setPlayerHeight(cursor.getFloat(cursor.getColumnIndex("height")));
            player.setPlayerWeight(cursor.getFloat(cursor.getColumnIndex("weight")));
            player.setPlayerTeam(team);
        }

        cursor.close();

        return player;
    }

    @SuppressLint("Range")
    @Override
    public List<Player> listEntry() throws SQLException {
        List<Player> players = new ArrayList<>();

        String querySQL = "SELECT "
                + "player.id, player.name, player.birthDate, player.height, player.weight, "
                + "team.id AS team_id, team.name AS team_name, team.cityName AS team_cityName "
                + "FROM player, team "
                + "WHERE player.teamId = team.id ";

        Cursor cursor = db.rawQuery(querySQL, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        while (!cursor.isAfterLast()) {
            Team team = new Team(
                    cursor.getInt(cursor.getColumnIndex("team_id")),
                    cursor.getString(cursor.getColumnIndex("team_name")),
                    cursor.getString(cursor.getColumnIndex("team_cityName"))
            );

            Player player = new Player(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    LocalDate.parse(
                            cursor.getString(cursor.getColumnIndex("birthDate"))),
                    cursor.getFloat(cursor.getColumnIndex("height")),
                    cursor.getFloat(cursor.getColumnIndex("weight")),
                    team
            );

            players.add(player);

            cursor.moveToNext();
        }

        cursor.close();

        return players;
    }
}

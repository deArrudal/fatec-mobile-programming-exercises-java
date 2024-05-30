package com.example.registerplayer.controller;

import com.example.registerplayer.model.Team;
import com.example.registerplayer.persistence.TeamDAO;

import java.sql.SQLException;
import java.util.List;

public class TeamController implements IController<Team> {
    private final TeamDAO teamDAO;

    public TeamController(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    @Override
    public void registerEntry(Team team) throws SQLException {
        if (teamDAO.open() == null) {
            teamDAO.open();
        }

        teamDAO.registerEntry(team);

        teamDAO.close();
    }

    @Override
    public void updateEntry(Team team) throws SQLException {
        if (teamDAO.open() == null) {
            teamDAO.open();
        }

        teamDAO.updateEntry(team);

        teamDAO.close();
    }

    @Override
    public void removeEntry(Team team) throws SQLException {
        if (teamDAO.open() == null) {
            teamDAO.open();
        }

        teamDAO.removeEntry(team);

        teamDAO.close();
    }

    @Override
    public Team searchEntry(Team team) throws SQLException {
        if (teamDAO.open() == null) {
            teamDAO.open();
        }

        return teamDAO.searchEntry(team);
    }

    @Override
    public List<Team> listEntry() throws SQLException {
        if (teamDAO.open() == null) {
            teamDAO.open();
        }

        return teamDAO.listEntry();
    }
}

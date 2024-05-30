package com.example.registerplayer.controller;

import com.example.registerplayer.model.Player;
import com.example.registerplayer.persistence.PlayerDAO;

import java.sql.SQLException;
import java.util.List;

public class PlayerController implements IController<Player> {
    private final PlayerDAO playerDAO;

    public PlayerController(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    @Override
    public void registerEntry(Player player) throws SQLException {
        if (playerDAO.open() == null) {
            playerDAO.open();
        }

        playerDAO.registerEntry(player);

        playerDAO.close();
    }

    @Override
    public void updateEntry(Player player) throws SQLException {
        if (playerDAO.open() == null) {
            playerDAO.open();
        }

        playerDAO.updateEntry(player);

        playerDAO.close();
    }

    @Override
    public void removeEntry(Player player) throws SQLException {
        if (playerDAO.open() == null) {
            playerDAO.open();
        }

        playerDAO.removeEntry(player);

        playerDAO.close();
    }

    @Override
    public Player searchEntry(Player player) throws SQLException {
        if (playerDAO.open() == null) {
            playerDAO.open();
        }

        return playerDAO.searchEntry(player);
    }

    @Override
    public List<Player> listEntry() throws SQLException {
        if (playerDAO.open() == null) {
            playerDAO.open();
        }

        return playerDAO.listEntry();
    }
}

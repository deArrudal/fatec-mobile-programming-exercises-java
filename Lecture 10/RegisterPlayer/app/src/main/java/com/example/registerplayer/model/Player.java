package com.example.registerplayer.model;

import androidx.annotation.NonNull;

import java.time.LocalDate;

public class Player {
    private int playerID;
    private String playerName;
    private LocalDate playerBirthDate;
    private float playerHeight;
    private float playerWeight;
    private Team playerTeam;

    public Player(int playerID) {
        this.playerID = playerID;
    }

    public Player(int playerID, String playerName, LocalDate playerBirthDate, float playerHeight,
                  float playerWeight, Team playerTeam) {
        this.playerID = playerID;
        this.playerName = playerName;
        this.playerBirthDate = playerBirthDate;
        this.playerHeight = playerHeight;
        this.playerWeight = playerWeight;
        this.playerTeam = playerTeam;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public LocalDate getPlayerBirthDate() {
        return playerBirthDate;
    }

    public void setPlayerBirthDate(LocalDate playerBirthDate) {
        this.playerBirthDate = playerBirthDate;
    }

    public float getPlayerHeight() {
        return playerHeight;
    }

    public void setPlayerHeight(float playerHeight) {
        this.playerHeight = playerHeight;
    }

    public float getPlayerWeight() {
        return playerWeight;
    }

    public void setPlayerWeight(float playerWeight) {
        this.playerWeight = playerWeight;
    }

    public Team getPlayerTeam() {
        return playerTeam;
    }

    public void setPlayerTeam(Team playerTeam) {
        this.playerTeam = playerTeam;
    }

    @NonNull
    @Override
    public String toString() {
        return playerID + " - " + playerName + " - " + playerTeam.getTeamName();
    }
}

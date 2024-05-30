package com.example.registerplayer.model;

import androidx.annotation.NonNull;

public class Team {
    private int teamID;
    private String teamName;
    private String teamCity;

    public Team(int teamID) {
        this.teamID = teamID;
    }

    public Team(int teamID, String teamName, String teamCity) {
        this.teamID = teamID;
        this.teamName = teamName;
        this.teamCity = teamCity;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamCity() {
        return teamCity;
    }

    public void setTeamCity(String teamCity) {
        this.teamCity = teamCity;
    }

    @NonNull
    @Override
    public String toString() {
        return teamID + " - " + teamName;
    }
}

package com.example.bookrental.model;

import androidx.annotation.NonNull;

public abstract class Exemplar {
    private int exemplarId;
    private String exemplarName;
    private int exemplarPages;

    public Exemplar(int exemplarId, String exemplarName, int exemplarPages) {
        this.exemplarId = exemplarId;
        this.exemplarName = exemplarName;
        this.exemplarPages = exemplarPages;
    }

    public int getExemplarId() {
        return exemplarId;
    }

    public void setExemplarId(int exemplarId) {
        this.exemplarId = exemplarId;
    }

    public String getExemplarName() {
        return exemplarName;
    }

    public void setExemplarName(String exemplarName) {
        this.exemplarName = exemplarName;
    }

    public int getExemplarPages() {
        return exemplarPages;
    }

    public void setExemplarPages(int exemplarPages) {
        this.exemplarPages = exemplarPages;
    }

    @NonNull
    @Override
    public String toString() {
        return exemplarId + " - " + exemplarName;
    }
}

package com.example.bookrental.model;

import androidx.annotation.NonNull;

public class Magazine extends Exemplar {
    private String magazineISSN;

    public Magazine(int exemplarId, String exemplarName, int exemplarPages, String magazineISSN) {
        super(exemplarId, exemplarName, exemplarPages);
        this.magazineISSN = magazineISSN;
    }

    public String getMagazineISSN() {
        return magazineISSN;
    }

    public void setMagazineISSN(String magazineISSN) {
        this.magazineISSN = magazineISSN;
    }

    @NonNull
    @Override
    public String toString() {
        return getExemplarId() + " - " + getExemplarName();
    }
}

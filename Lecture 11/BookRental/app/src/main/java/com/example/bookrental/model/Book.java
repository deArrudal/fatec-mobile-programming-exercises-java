package com.example.bookrental.model;

import androidx.annotation.NonNull;

public class Book extends Exemplar {
    private String bookISBN;
    private int bookEdition;

    public Book(int exemplarId, String exemplarName, int exemplarPages, String bookISBN,
                int bookEdition) {
        super(exemplarId, exemplarName, exemplarPages);
        this.bookISBN = bookISBN;
        this.bookEdition = bookEdition;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public int getBookEdition() {
        return bookEdition;
    }

    public void setBookEdition(int bookEdition) {
        this.bookEdition = bookEdition;
    }

    @NonNull
    @Override
    public String toString() {
        return getExemplarId() + " - " + getExemplarName();
    }
}

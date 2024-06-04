package com.example.bookrental.model;

import androidx.annotation.NonNull;

import java.time.LocalDate;

public class Rental {
    private Student rentalStudent;
    private Exemplar rentalExemplar;
    private LocalDate rentalDateWithdraw;
    private LocalDate rentalDateReturn;

    public Rental() {
        super();
    }

    public Rental(Student rentalStudent, Exemplar rentalExemplar, LocalDate rentalDateWithdraw) {
        this.rentalStudent = rentalStudent;
        this.rentalExemplar = rentalExemplar;
        this.rentalDateWithdraw = rentalDateWithdraw;
    }

    public Rental(Student rentalStudent, Exemplar rentalExemplar, LocalDate rentalDateWithdraw, LocalDate rentalDateReturn) {
        this.rentalStudent = rentalStudent;
        this.rentalExemplar = rentalExemplar;
        this.rentalDateWithdraw = rentalDateWithdraw;
        this.rentalDateReturn = rentalDateReturn;
    }

    public Student getRentalStudent() {
        return rentalStudent;
    }

    public void setRentalStudent(Student rentalStudent) {
        this.rentalStudent = rentalStudent;
    }

    public Exemplar getRentalExemplar() {
        return rentalExemplar;
    }

    public void setRentalExemplar(Exemplar rentalExemplar) {
        this.rentalExemplar = rentalExemplar;
    }

    public LocalDate getRentalDateWithdraw() {
        return rentalDateWithdraw;
    }

    public void setRentalDateWithdraw(LocalDate rentalDateWithdraw) {
        this.rentalDateWithdraw = rentalDateWithdraw;
    }

    public LocalDate getRentalDateReturn() {
        return rentalDateReturn;
    }

    public void setRentalDateReturn(LocalDate rentalDateReturn) {
        this.rentalDateReturn = rentalDateReturn;
    }

    @NonNull
    @Override
    public String toString() {
        return "Rental{" +
                "Student='" + rentalStudent.getStudentName() + "', " +
                "Exemplar='" + rentalExemplar.getExemplarName() + "', " +
                "Date='" + rentalDateWithdraw + "'}";
    }
}

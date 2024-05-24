package com.example.swimmingcompetition.model;

import androidx.annotation.NonNull;

import java.time.LocalDate;

public class Junior extends Swimmer {
    private int swimmerYearPractice;

    public Junior(String swimmerName, LocalDate swimmerBirthDate, String swimmerAddress,
                  int swimmerYearPractice) {
        super(swimmerName, swimmerBirthDate, swimmerAddress);
        this.swimmerYearPractice = swimmerYearPractice;
    }

    public int getSwimmerYearPractice() {
        return swimmerYearPractice;
    }

    public void setSwimmerYearPractice(int swimmerYearPractice) {
        this.swimmerYearPractice = swimmerYearPractice;
    }

    @NonNull
    @Override
    public String toString() {
        return "Name='" + getSwimmerName() + '\'' +
                ", BirthDate='" + getSwimmerBirthDate() + '\'' +
                ", Address='" + getSwimmerAddress() + '\'' +
                ", YearPractice='" + swimmerYearPractice + '\'';
    }
}

package com.example.swimmingcompetition.model;

import androidx.annotation.NonNull;

import java.time.LocalDate;

public class Senior extends Swimmer {
    private boolean swimmerIsCardiac;

    public Senior(String swimmerName, LocalDate swimmerBirthDate, String swimmerAddress,
                  boolean swimmerIsCardiac) {
        super(swimmerName, swimmerBirthDate, swimmerAddress);
        this.swimmerIsCardiac = swimmerIsCardiac;
    }

    public boolean isSwimmerIsCardiac() {
        return swimmerIsCardiac;
    }

    public void setSwimmerIsCardiac(boolean swimmerIsCardiac) {
        this.swimmerIsCardiac = swimmerIsCardiac;
    }

    @NonNull
    @Override
    public String toString() {
        return "Name='" + getSwimmerName() + '\'' +
                ", BirthDate='" + getSwimmerBirthDate() + '\'' +
                ", Address='" + getSwimmerAddress() + '\'' +
                ", IsCardiac='" + swimmerIsCardiac + '\'';
    }
}

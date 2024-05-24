package com.example.swimmingcompetition.model;

import androidx.annotation.NonNull;

import java.time.LocalDate;

public abstract class Swimmer {
    private String swimmerName;
    private LocalDate swimmerBirthDate;
    private String swimmerAddress;

    public Swimmer(String swimmerName, LocalDate swimmerBirthDate, String swimmerAddress) {
        this.swimmerName = swimmerName;
        this.swimmerBirthDate = swimmerBirthDate;
        this.swimmerAddress = swimmerAddress;
    }

    public String getSwimmerName() {
        return swimmerName;
    }

    public void setSwimmerName(String swimmerName) {
        this.swimmerName = swimmerName;
    }

    public LocalDate getSwimmerBirthDate() {
        return swimmerBirthDate;
    }

    public void setSwimmerBirthDate(LocalDate swimmerBirthDate) {
        this.swimmerBirthDate = swimmerBirthDate;
    }

    public String getSwimmerAddress() {
        return swimmerAddress;
    }

    public void setSwimmerAddress(String swimmerAddress) {
        this.swimmerAddress = swimmerAddress;
    }

    @NonNull
    @Override
    public String toString() {
        return "Name='" + swimmerName + '\'' +
                ", BirthDate='" + swimmerBirthDate + '\'' +
                ", Address='" + swimmerAddress + '\'';
    }
}

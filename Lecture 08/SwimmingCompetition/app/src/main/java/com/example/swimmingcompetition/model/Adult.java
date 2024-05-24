package com.example.swimmingcompetition.model;

import androidx.annotation.NonNull;

import java.time.LocalDate;

public class Adult extends Swimmer {
    private String swimmerGym;
    private int swimmerBestTime;

    public Adult(String swimmerName, LocalDate swimmerBirthDate, String swimmerAddress,
                 String swimmerGym, int swimmerBestTime) {
        super(swimmerName, swimmerBirthDate, swimmerAddress);
        this.swimmerGym = swimmerGym;
        this.swimmerBestTime = swimmerBestTime;
    }

    public String getSwimmerGym() {
        return swimmerGym;
    }

    public void setSwimmerGym(String swimmerGym) {
        this.swimmerGym = swimmerGym;
    }

    public int getSwimmerBestTime() {
        return swimmerBestTime;
    }

    public void setSwimmerBestTime(int swimmerBestTime) {
        this.swimmerBestTime = swimmerBestTime;
    }

    @NonNull
    @Override
    public String toString() {
        return "Name='" + getSwimmerName() + '\'' +
                ", BirthDate='" + getSwimmerBirthDate() + '\'' +
                ", Address='" + getSwimmerAddress() + '\'' +
                ", Gym='" + swimmerGym + '\'' +
                ", BestTime='" + swimmerBestTime + '\'';
    }
}

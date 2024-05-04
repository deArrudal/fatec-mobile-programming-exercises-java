package com.example.checksalary.model;

import androidx.annotation.NonNull;

public abstract class Professor {
    private String professorName;
    private String professorIdentification;
    private int professorAge;

    public Professor(String professorName, String professorIdentification, int professorAge) {
        this.professorName = professorName;
        this.professorIdentification = professorIdentification;
        this.professorAge = professorAge;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getProfessorIdentification() {
        return professorIdentification;
    }

    public void setProfessorIdentification(String professorIdentification) {
        this.professorIdentification = professorIdentification;
    }

    public int getProfessorAge() {
        return professorAge;
    }

    public void setProfessorAge(int professorAge) {
        this.professorAge = professorAge;
    }

    public abstract double computeSalary();

}

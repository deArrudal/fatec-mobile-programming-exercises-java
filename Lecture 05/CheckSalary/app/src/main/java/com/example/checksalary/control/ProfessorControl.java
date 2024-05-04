package com.example.checksalary.control;

import android.annotation.SuppressLint;

import com.example.checksalary.model.Professor;
import com.example.checksalary.model.TemporaryProfessor;
import com.example.checksalary.model.TitularProfessor;

import java.util.HashMap;

public class ProfessorControl {
    private final HashMap<String, Professor> professors;

    public ProfessorControl() {
        this.professors = new HashMap<>();
        populateList();
    }

    private void populateList() {
        professors.put("001", new TitularProfessor("John Doe","001", 32, 17, 2500.00));
        professors.put("002", new TitularProfessor("Mike O'Malley","002", 27, 10, 2500.00));
        professors.put("011", new TemporaryProfessor("Mary Kate","011", 22, 30, 17));
        professors.put("012", new TemporaryProfessor("Kate Willows","012", 25, 40, 25.00));
    }

    @SuppressLint("DefaultLocale")
    public String checkSalary(String identification) {
        Professor professor = professors.get(identification);
        if(professor != null) return String.format("$ %.2f", professor.computeSalary());
        else return "Invalid Identification";

    }
}

package com.example.checksalary.model;

public class TitularProfessor extends Professor {
    private int professorYearsWorked;
    private double professorBaseSalary;

    public TitularProfessor(String professorName, String professorIdentification, int professorAge,
                            int professorYearsWorked, double professorBaseSalary) {
        super(professorName, professorIdentification, professorAge);
        this.professorYearsWorked = professorYearsWorked;
        this.professorBaseSalary = professorBaseSalary;
    }

    public int getProfessorYearsWorked() {
        return professorYearsWorked;
    }

    public void setProfessorYearsWorked(int professorYearsWorked) {
        this.professorYearsWorked = professorYearsWorked;
    }

    public double getProfessorBaseSalary() {
        return professorBaseSalary;
    }

    public void setProfessorBaseSalary(double professorBaseSalary) {
        this.professorBaseSalary = professorBaseSalary;
    }

    @Override
    public double computeSalary() {
        return Math.pow(1.05, ((double) (professorYearsWorked / 5))) * professorBaseSalary;
    }
}

package com.example.checksalary.model;

public class TemporaryProfessor extends Professor{
    private int professorHoursWorked;
    private double professorHourlyRate;

    public TemporaryProfessor(String professorName, String professorIdentification,
                              int professorAge, int professorHoursWorked,
                              double professorHourlyRate) {
        super(professorName, professorIdentification, professorAge);
        this.professorHoursWorked = professorHoursWorked;
        this.professorHourlyRate = professorHourlyRate;
    }

    public int getProfessorHoursWorked() {
        return professorHoursWorked;
    }

    public void setProfessorHoursWorked(int professorHoursWorked) {
        this.professorHoursWorked = professorHoursWorked;
    }

    public double getProfessorHourlyRate() {
        return professorHourlyRate;
    }

    public void setProfessorHourlyRate(double professorHourlyRate) {
        this.professorHourlyRate = professorHourlyRate;
    }

    @Override
    public double computeSalary() {
        return professorHourlyRate * professorHoursWorked;
    }
}

package domain;

import java.time.LocalDate;
//import domain.Tema;

public class Nota {
    private final LocalDate date;
    private String profesor;
    private Student student;
    private Tema tema;
    private double value;

    public Nota(Student student, Tema tema, double value, LocalDate date, String profesor){
        this.student = student;
        this.tema = tema;
        this.value = value;
        this.profesor = profesor;
        this.date = date;

    }

    public String getProfesor() {
        return profesor;
    }

    public Student getStudent() {
        return student;
    }

    public Tema getTema() {
        return tema;
    }

    public double getValue() {
        return value;
    }
}

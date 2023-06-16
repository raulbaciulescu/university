package domain;

import java.time.LocalDate;

public class Nota {
    private final LocalDate date;
    private String profesor;
    private Student student;
    private Tema tema;
    private double value;

    public Nota(Student student, Tema tema, double value, LocalDate date, String profesor) {
        this.date = date;
        this.profesor = profesor;
        this.student = student;
        this.tema = tema;
        this.value = value;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public void setValue(double value) {
        this.value = value;
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

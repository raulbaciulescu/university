package com.example.domain;

public class NotaDto {
    private String studentName;
    private String temaId;
    private double nota;
    private final String profesor;

    public NotaDto(String studentName, String temaId, double nota, String profesor) {
        this.studentName = studentName;
        this.temaId = temaId;
        this.nota = nota;
        this.profesor=profesor;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTemaId() {
        return temaId;
    }

    public void setTemaId(String temaId) {
        this.temaId = temaId;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getProfesor() {
        return profesor;
    }

    @Override
    public String toString() {
        return "NotaDto{" +
                "studentName='" + studentName + '\'' +
                ", temaId='" + temaId + '\'' +
                ", nota=" + nota +
                ", profesor='" + profesor + '\'' +
                '}';
    }
}

package models;

import java.util.Objects;

public class Student {
    private String nume;
    private float medie;

    public Student(String nume, float medie) {
        this.nume = nume;
        this.medie = medie;
    }

    public String getNume() {
        return nume;
    }

    public float getMedie() {
        return medie;
    }

    @Override
    public String toString() {
        return "Student{" +
                "nume='" + nume + '\'' +
                ", medie=" + medie +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || o.getClass() != this.getClass())
            return false;
        Student student = (Student) o;
        return Objects.equals(nume, student.nume) && Float.compare(medie, student.medie) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, medie);
    }

}

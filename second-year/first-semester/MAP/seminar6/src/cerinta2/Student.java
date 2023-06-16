package cerinta2;

public class Student {
    private String name;
    private double grade;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }

    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }

    public Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }
}

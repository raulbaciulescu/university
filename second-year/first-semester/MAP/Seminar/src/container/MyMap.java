package container;

import models.Student;

import java.util.*;

public class MyMap {
    //Map<Integer, List<Student>> map;
    TreeMap<Integer, List<Student>> map;
    private ComparatorMedie comparatorMedie = new ComparatorMedie();
    public MyMap() {
        map = new TreeMap<Integer, List<Student>>((Map<? extends Integer, ? extends List<Student>>) comparatorMedie);
    }

    public void add(Student student) {
        int medie = Math.round(student.getMedie());
        List<Student> students = map.get(medie);
        if (students != null)
            students.add(student);
        else {
            students = new ArrayList<>();
            students.add(student);
            map.put(medie, students);
        }
    }
    public void printAll() {
        for (Map.Entry<Integer, List<Student>> pair : map.entrySet())
            System.out.println(pair.getKey() + " " + pair.getValue().toString());
    }
    static class ComparatorMedie implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            System.out.println("Comparator Medie   ");
            return Math.round(o1.getMedie()) - Math.round(o2.getMedie());
        }
    }
}

package Container;

import models.Student;

import java.util.*;

public class MyMap {
    Map<Integer, List<Student>> map;

    public MyMap() {
        map = new TreeMap<Integer, List<Student>>();
    }

    public void add(Student student) {
        int medie = Math.round(student.getMedie());

        List<Student> students = map.get(medie);
        if(students != null)
            students.add(student);
        else {
            students = new ArrayList<Student>();
            students.add(student);
            map.put(medie, students);
        }
    }

    static class ComparatorMedie implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return Math.round(o1.getMedie()) - Math.round(o2.getMedie());
        }
    }

    public void printAll() {
        for (Map.Entry<Integer, List<Student>> pair:
        map.entrySet()) {
            System.out.println(pair.getKey() + " " + pair.getValue().toString());
        }
    }
}
package com.company;

import Container.MyMap;
import models.Student;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Student s1= new Student("Dan", 4.5f);
        Student s2= new Student("Ana", 8.5f);
        Student s3= new Student("Dani", 5.5f);
        Student s4= new Student("Danu", 5.6f);

        Set<Student> c = new HashSet<>();
        c.add(s1);
        c.add(s2);
        c.add(s3);

        for (Student s : c) {
            System.out.println(s);
        }

        Comparator<Student> comp = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getNume().compareTo(o2.getNume());
            }
        };

        Set<Student> set2 = new TreeSet<>(comp);
        set2.add(s1);
        set2.add(s2);
        set2.add(s3);
        for (Student s : set2) {
            System.out.println(s);
        }

        TreeMap<String, Student> map = new TreeMap<String, Student>();

        map.put(s1.getNume(), s1);
        map.put(s2.getNume(), s2);
        map.put(s3.getNume(), s3);

        for(Map.Entry<String, Student> pair : map.entrySet())
        {
            System.out.println(pair.getKey() + " " + pair.getValue() + "\n");
        }


        MyMap myMap = new MyMap();
        myMap.add(s1);
        myMap.add(s2);
        myMap.add(s3);
        myMap.add(s4);

        myMap.printAll();
    }

}

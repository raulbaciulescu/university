import com.sun.source.tree.Tree;
import container.MyMap;
import models.Student;

import java.util.*;

public class Main {


    public static void main(String[] args) {
        Student s1 = new Student("Dan", 4.5f);
        Student s2 = new Student("Ana", 8.5f);
        Student s3 = new Student("Dani", 4.5f);
        Student s4 = new Student("Dano", 5.5f);

        HashSet<Student> studentHashSet = new HashSet<>();
        studentHashSet.add(s1);
        studentHashSet.add(s2);
        studentHashSet.add(s3);
        studentHashSet.addAll(Arrays.asList(s1, s2, s3));
        for (Student s: studentHashSet)
            System.out.println(s);
        System.out.println("-------------------------------------------");
        Comparator<Student> comparator = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                System.out.println("Comparator normal   ");
                return o1.getNume().compareTo(o2.getNume());
            }
        };

        TreeSet<Student> studentTreeSet = new TreeSet<>(comparator);
        studentTreeSet.addAll(Arrays.asList(s1, s2, s3));
        System.out.println(studentTreeSet);
        System.out.println("-------------------------------------------");
//        HashMap<Integer, Student> studentHashMap = new HashMap<Integer, Student>();
//        studentHashMap.put(1, s1);
//        studentHashMap.put(2, s2);
//        studentHashMap.put(3, s3);
//        System.out.println("Hash map: " + studentHashMap);


        TreeMap<String, Student> studentTreeMap = new TreeMap<String, Student>();
        studentTreeMap.put(s1.getNume(), s1);
        studentTreeMap.put(s2.getNume(), s2);
        studentTreeMap.put(s3.getNume(), s3);
        //System.out.println(studentTreeMap);
        for (Map.Entry<String, Student> pair : studentTreeMap.entrySet())
            System.out.println(pair.getKey() + " " + pair.getValue().getMedie());

        MyMap myMap = new MyMap();
        myMap.add(s1);
        myMap.add(s2);
        myMap.add(s3);
        myMap.add(s4);
        myMap.printAll();
    }
}

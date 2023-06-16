package ro.ubb.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
p1.
 * * - create a new "ro.ubb.reflection.Student" instance (reflection).
 * * - initialize the student's private attributes ("name", "groupNumber") with the values ("john", 123).
 * * - print the student instance.


p2.
 * * - create a new "ro.ubb.reflection.Student" instance (reflection)
 * * - invoke setName("john") and setGroupNumber(123)
 * * - print the student instance.


p3.
 * * - create a new "ro.ubb.reflection.Student" instance ("john",123) by invoking the constructor
 * * - print the student instance.

p4.
 * * - given a Student instance ("John",123), print all attribute names, types, and values.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        p1();
        p4();
    }

    private static void p4() throws IllegalAccessException {
        //Class studentClass = Class.forName("ro.ubb.reflection.Student");
        Student student = new Student("John", 123);
        //Field[] attributes = Student.class.getDeclaredFields();
        Field[] attributes = student.getClass().getDeclaredFields();
        for (Field field : attributes) {
            System.out.println("ATTRIBUTE NAME: " + field.getName());
            System.out.println("ATTRIBUTE NAME: " + field.getType());
            field.setAccessible(true);
            System.out.println("ATTRIBUTE NAME: " + field.get(student));
            field.setAccessible(false);
            System.out.println();
        }
    }

    private static void p3() {

    }

    private static void p2() {
    }

    private static void p1() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        Class studentClass = Class.forName("ro.ubb.reflection.Student");
        //Student student = (Student) studentClass.newInstance();
        //Student student = Student.class.getDeclaredConstructor().newInstance();
        Student student = (Student) studentClass.getDeclaredConstructor().newInstance();

        Field nameField = studentClass.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(student, "John");
        nameField.setAccessible(false);

        Field groupField = studentClass.getDeclaredField("groupNumber");
        groupField.setAccessible(true);
        groupField.set(student, 123);
        groupField.setAccessible(false);

        System.out.println(student);
    }


}

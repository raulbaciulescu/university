import cerinta2.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {

    public static <E> void  printArea(List<E> l, Area<E> f) {
        for (E entity: l) {
            System.out.println(f.area(entity));
        }
    }
    public static<E> void printList(List<E> l, Predicate<E> p) {
        l.forEach(x -> {
            if (p.test(x))
                System.out.println(x);
        });
    }
    public static void main(String[] args){

//        Area<Circle> circleArea = x -> Math.PI * Math.pow(x.getRadius(), 2);
//        List<Circle> circles = new ArrayList<Circle>();
//        circles.add(new Circle(5f));
//        circles.add(new Circle(4f));
//        circles.add(new Circle(3f));
//        printArea(circles, circleArea);
//
//
//        Area<Square> squareArea = x -> Math.pow(x.getL(), 2);
//        List<Square> squares = new ArrayList<>();
//        squares.add(new Square(2));
//        squares.add(new Square(5));
//        squares.add(new Square(3));
//        System.out.println("\n");
//        printArea(squares, squareArea);
//        System.out.println("ok");
//

        List<Student> students = new ArrayList<Student>();
        students.add(new Student("Ion", 9.45f));
        students.add(new Student("Marian", 7.45f));
        students.add(new Student("Ana", 10f));

        Predicate<Student> hasScholarship = x -> x.getGrade() > 9;
        printList(students, hasScholarship);
        Predicate<Student> hasScholarshipAndNameStartsWithA = hasScholarship.and(x->x.getName().startsWith("A"));
        printList(students, hasScholarshipAndNameStartsWithA);


//        Function<String, Integer> convertedLambda = x -> Integer.valueOf(x);
//        Function<String, Integer> convertedMethodReference = Integer::valueOf;



    }
}

import domain.Nota;
import domain.NotaDto;
import domain.Student;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import domain.Tema;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    private static List<Student> getStudents(){
        Student s1 = new Student(221, "Pop Andreea");
        s1.setId(1L);
        Student s2 = new Student(222, "Pop Andrei");
        s2.setId(2L);
        Student s3 = new Student(223, "Emanuel Macron");
        s3.setId(3L);
        Student s4 = new Student(224, "Gigi Halid");
        s4.setId(4L);
        List<Student> list = Arrays.asList(s1, s2, s3, s4);
        return list;
    }

    private static List<Tema> getTeme(){
        return Arrays.asList(
                new Tema("1","mate"),
                new Tema("2","mate1"),
                new Tema("3","info"),
                new Tema("4","info2")
                );
    }

    private static List<Nota> getNote(List<Student> stud, List<Tema> teme) {
        return Arrays.asList(
                new Nota(stud.get(0), teme.get(0), 10d, LocalDate.of(2019, 11, 2), "profesor1"),
                new Nota(stud.get(1), teme.get(0), 9d, LocalDate.of(2019, 11, 2).minusWeeks(1), "profesor1"),
                new Nota(stud.get(1), teme.get(1), 10d, LocalDate.of(2019, 10, 20), "profesor2"),
                new Nota(stud.get(1), teme.get(2), 10d, LocalDate.of(2019, 10, 20), "profesor2"),
                new Nota(stud.get(2), teme.get(1), 7d, LocalDate.of(2019, 10, 28), "profesor1"),
                new Nota(stud.get(2), teme.get(3), 9d, LocalDate.of(2019, 10, 27), "profesor2"),
                new Nota(stud.get(1), teme.get(3), 10d, LocalDate.of(2019, 10, 29), "profesor2")
        );
    }


    public static void main(String[] args) {

        List<Student> studentList = getStudents();
        for (Student x : studentList)
            System.out.println(x.toString());

        List<Nota> noteList = getNote(getStudents(), getTeme());
        report1(noteList);
        report2(noteList);
        report3(noteList);

    }

    /**
     * creati/afisati o lista de obiecte NotaDto apoi filtrati dupa un anumit profesor
     * (toate notele acordate de un anumit profesor)
     * (toate notele acordate de un anumit profesor, la o anumita grupa)
     * GENERALIZARE: FILTRU COMPUS
     */
    private static void report1(List<Nota> note) {
        Predicate<Nota> byGrupa= x->x.getStudent().getGroup()==221;
        Predicate<Nota> byProf=x->x.getProfesor().equals("profesor1");
        Predicate<Nota> filtered=byGrupa.and(byProf);
        note.stream()
                .filter(x->x.getStudent().getGroup()==221)
                .map(x->new NotaDto(x.getStudent().getName(),x.getTema().getId(),x.getValue(),x.getProfesor()))
                .forEach(x->System.out.println(x));

        note.stream()
                .filter(filtered)
                .map(x->new NotaDto(x.getStudent().getName(),x.getTema().getId(),x.getValue(),x.getProfesor()))
                .forEach(x->System.out.println(x));

    }

    /**
     * media notelor de lab pt fiecare student
     *
     * @param note
     */
    private static void report2(List<Nota> note) {

        Map<Student, List<Nota>> grouped=note.stream()
                .collect(Collectors.groupingBy(x->x.getStudent()));
        grouped.entrySet()
                .forEach(x->{
                    System.out.print(x.getKey().getName());
                    Double suma=x.getValue()
                            .stream()
                            .map(y->y.getValue())
                            .reduce(0d, (a,b)->a+b);
                    System.out.println(suma/10);
                });

    }

    private static void report3(List<Nota> note){
        double medieMax = 0;
        Map<Tema, List<Nota>> grouped = note.stream()
                .collect(Collectors.groupingBy(x->x.getTema()));
        grouped.entrySet()
                .forEach(x->{
                    Double medie = x.getValue()
                            .stream()
                            .map(y->y.getValue()).
                            reduce(0d, (a,b)->a+b);
                    medie = medie / x.getValue().size();
                    System.out.println(medie);
                });
    }

}

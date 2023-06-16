package ro.ubbcluj.map.functional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class TestFunctional {
    public static double calculateSum(double a, double b) {
        return a + b;
    }

    public static void main(String[] args) {
        AritmeticExpression aritmeticExpression = new AritmeticExpression() {
            @Override
            public double calculate(double a, double b) {
                return a + b;
            }
        };
        //System.out.println(aritmeticExpression.calculate(2, 3));

        AritmeticExpression aritmeticExpression1 = TestFunctional::calculateSum; //referinta la metoda calculateSum
        //System.out.println(aritmeticExpression1.calculate(5, 7));

        AritmeticExpression aritmeticExpression2 = (double a, double b)->{return a + b;};
        //System.out.println(aritmeticExpression2.calculate(4, 1));

        AritmeticExpression aritmeticExpression3 = (double a, double b)-> a + b;
        //System.out.println(aritmeticExpression2.calculate(4, 1));

        AritmeticExpression aritmeticExpression4 = (a, b)-> a + b;
        //System.out.println(aritmeticExpression2.calculate(4, 1));

        Predicate<Integer> testNumber = a -> a % 2 == 0;
        System.out.println(testNumber.test(2));
        System.out.println(testNumber.test(7));

        // sa se stearga dintr-o lista de siruri de caractere toate elementele care incep cu caracterul 'a'
        // sa se stearga dintr-o lista de siruri de caractere toate elementele care sunt prefixele unui element dat, cu lamba si referinta la metoda

        List<String> list1 = new ArrayList(Arrays.asList("ana", "are", "o", "atitudine", "impozanta"));
        list1.removeIf(s->s.startsWith("a"));
        list1.forEach(x -> System.out.println(x));
        list1.forEach(System.out::println);
    }
}

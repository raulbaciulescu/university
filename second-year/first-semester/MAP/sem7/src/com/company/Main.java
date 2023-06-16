package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("asf", "bcd", "bed", "bbb");
        list.stream().filter(x->{
            System.out.println("Filter: " + x);
            return x.startsWith("b");
        }).map(x -> {
            System.out.println("Map: " + x);
            return x.toUpperCase();
        }).forEach(x -> {
            System.out.println("forEach:\n" + x);
        });

        System.out.println("Exercitiul2\n");
        String rez1 = list.stream().filter(x->{
            return x.startsWith("b");
        }).map(String::toUpperCase).reduce("", (x, y) -> x + y);
        System.out.println(rez1);


        System.out.println("Exercitiul 3\n");
        Optional<String> rez = list.stream().filter(x->{
            return x.startsWith("b");
        }).map(String::toUpperCase).reduce((x, y) -> x + y);
        if (!rez.isEmpty())
            System.out.println(rez.get());
        rez.ifPresent(System.out::println);

    }
}

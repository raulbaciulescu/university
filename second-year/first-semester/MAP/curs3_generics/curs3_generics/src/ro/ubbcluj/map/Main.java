package ro.ubbcluj.map;

import ro.ubbcluj.map.model.Utilizator;
import ro.ubbcluj.map.model.validators.UserValidator;
import ro.ubbcluj.map.repository.InMemoryRepository;
import ro.ubbcluj.map.repository.Repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void loadData() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("data/text.in"));
            String linie;
            while ((linie = br.readLine()) != null) {
                System.out.println(linie);
            }
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //------------------------------------------------------------------------------------------
        try (BufferedReader br2 = new BufferedReader(new FileReader("data/text.in"))) {
            br = new BufferedReader(new FileReader("data/text.in"));
            String linie;
            while ((linie = br.readLine()) != null) {
                System.out.println(linie);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

//        Utilizator user = new Utilizator("Ionica", "Pop");
//        user.setId(123L);
//        Repository<Long, Utilizator> repo = new InMemoryRepository<>(new UserValidator());
//        repo.save(user);
//        System.out.println(repo.findAll());
//        System.out.println("ok");
        int a = 0;
        try {
            Integer.parseInt("12b");
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("In finally!, mereu executat");
        }

        loadData();
    }
}

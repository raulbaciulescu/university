package ro.ubbcluj.map;

import ro.ubbcluj.map.domain.Utilizator;
import ro.ubbcluj.map.domain.validators.UtilizatorValidator;
import ro.ubbcluj.map.repository.Repository;
import ro.ubbcluj.map.repository.db.UtilizatorDbRepository;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("ok");
        Repository<Long, Utilizator> repoDb = new UtilizatorDbRepository("jdbc:postgresql://localhost:5432/academic",
                "postgres", "266259", new UtilizatorValidator());
        repoDb.findAll().forEach(System.out::println);

        //Utilizator u = new Utilizator("f1", "l1");
       // repoDb.save(u);
       // repoDb.findAll().forEach(System.out::println);

        Utilizator u = new Utilizator("new first", "new last");
        u.setId(1L);
        repoDb.update(u);
        repoDb.findAll().forEach(System.out::println);

    }
}

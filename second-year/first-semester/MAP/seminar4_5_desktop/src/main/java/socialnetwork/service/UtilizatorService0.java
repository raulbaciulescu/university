package socialnetwork.service;

import socialnetwork.domain.Utilizator;
import socialnetwork.repository.Repository0;

public class UtilizatorService0 {
    private Repository0<Long, Utilizator> repo;

    public UtilizatorService0(Repository0<Long, Utilizator> repo) {
        this.repo = repo;
    }

    public Utilizator addUser(String firstName, String lastName) {
        Utilizator user = repo.save(new Utilizator(firstName, lastName));
        //eventual o validare
        return user;
    }

    public Iterable<Utilizator> getAll(){
        return repo.findAll();
    }
}

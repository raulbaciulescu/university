package exam.persistence;

import exam.model.Entity;

import java.util.List;

public interface Repository<ID, E extends Entity<ID>>{
    E add(E elem);
    void delete(ID id);
    void update(E elem);
    E findById(ID id);
    List<E> findAll();
}

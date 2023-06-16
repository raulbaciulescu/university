package api;

import domain.Entity;

import java.util.List;
import java.util.Optional;

public interface Table<ID, T extends Entity<ID>> {
    void add(T elem);
    void delete(ID id);
    void update(T elem, T newElem);
    Optional<T> findById(ID id);
    List<T> getAll();
}

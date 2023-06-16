package api;

import domain.MyEntity;

import java.util.List;
import java.util.Optional;

public interface Repository<ID, T extends MyEntity<ID>> {
    void add(T entity);
    void update (T entity, T newEntity);
    void delete(ID id);
    Optional<T> findByID(ID id);
    List<T> getAll();
}

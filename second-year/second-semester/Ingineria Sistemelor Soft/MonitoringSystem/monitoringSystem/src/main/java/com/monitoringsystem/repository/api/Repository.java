package com.monitoringsystem.repository.api;

import com.monitoringsystem.model.Entity;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Repository<ID, T extends Entity<ID>> {
    void add(T entity);
    void update (T entity, T newEntity);
    void delete(ID id);
    Optional<T> findByID(ID id) throws SQLException;
    List<T> getAll();
}

package com.example.socialapp.repository;

import com.example.socialapp.domain.Entity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public interface Repository<ID, T extends Entity<ID>> {

    void add(final T entity) throws SQLException;

    void update (final T entity) throws SQLException;

    Optional<T> findByID(final ID id) throws SQLException;

    void delete(final ID id) throws SQLException;

    ArrayList<T> getAll() throws SQLException;
}

package com.example.socialapp.database.table;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public interface Table<ID, T> {
    void insert(final T entity) throws SQLException;

    Optional<T> findByID(final ID id) throws SQLException;

    void update(final T entity) throws SQLException;

    void delete(final ID id) throws SQLException;

    ArrayList<T> getAll() throws SQLException;
}

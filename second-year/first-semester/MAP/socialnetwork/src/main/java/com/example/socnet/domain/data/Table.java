package com.example.socnet.domain.data;

import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public interface Table<ID, T> {

    void insert(@NotNull final T object) throws SQLException;

    @NotNull
    Optional<T> whereID(@NotNull final ID id) throws SQLException;

    @NotNull
    Optional<T> fromResultSet(@NotNull final ResultSet resultSet) throws SQLException;

    void update(@NotNull final T object) throws SQLException;

    void delete(@NotNull final ID id) throws SQLException;

    @NotNull
    ArrayList<T> getAll() throws SQLException;
}

package com.example.socnet.repository.memory;


import com.example.socnet.domain.model.Entity;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class Repository<ID, T extends Entity<ID>>
        implements com.example.socnet.repository.Repository<ID, T> {

    @NotNull
    private final Map<ID, T> entities;

    public Repository() {
        this.entities = new HashMap<>();
    }

    @NotNull
    @Override
    public Optional<T> add(@NotNull final T entity) throws SQLException {
//        if (entity.getId() != null && this.findByID(entity.getId()).isPresent()) {
//            return Optional.empty();
//        }

        this.entities.put(entity.getId(), entity);
        return Optional.of(entity);
    }

    @NotNull
    @Override
    public Optional<T> update(@NotNull final T entity) throws SQLException {
        if (entity.getId() == null) {
            return Optional.empty();
        }

        final Optional<T> result = this.findByID(entity.getId());
        if (result.isEmpty()) {
            return result;
        }

        if (entity.equals(result.get())) {
            return Optional.empty();
        }

        this.entities.put(entity.getId(), entity);
        return Optional.of(entity);
    }

    @NotNull
    @Override
    public Optional<T> findByID(@NotNull final ID id) throws SQLException {
        return this.from(id, this.entities::get);
    }

    @NotNull
    @Override
    public Optional<T> delete(@NotNull final ID id) throws SQLException {
        return this.from(id, this.entities::remove);
    }

    @NotNull
    private Optional<T> from(@NotNull final ID id,
                             @NotNull final Function<ID, T> function) {
        final T result = function.apply(id);
        return result != null
                ? Optional.of(result)
                : Optional.empty();
    }

    @NotNull
    @Override
    public ArrayList<T> getAll() throws SQLException {
        return new ArrayList<>(this.entities.values());
    }
}

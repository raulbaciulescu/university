package repository;

import domain.model.Entity;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public interface Repository<ID, T extends Entity<ID>> {

    /**
     * @param entity the object which will be added
     * @return Optional.of(entity) if the adding operation succeeded,
     * or Optional.empty() if failed
     */
    @NotNull
    Optional<T> add(@NotNull final T entity) throws SQLException;

    /**
     * @param entity the entity that has the same id with the one we want to update
     *               and new attributes
     * @return Optional.of(entity) if the update succeeded,
     * or Optional.empty() if failed
     */
    @NotNull
    Optional<T> update (@NotNull final T entity) throws SQLException;

    /**
     * @param id the id of the entity
     * @return Optional.of(entity) if the find operation succeeded,
     * where entity has the id that has been passed,
     * or Optional.empty() if failed
     */
    @NotNull
    Optional<T> findByID(@NotNull final ID id) throws SQLException;

    /**
     * @param id the id of the entity that will be deleted
     * @return Optional.of(entity) if the entity has been deleted,
     * or Optional.empty() if the entity hasn't been found
     */
    @NotNull
    Optional<T> delete(@NotNull final ID id) throws SQLException;

    /**
     * @return an ArrayList<T> that contains all the entities
     */
    @NotNull
    ArrayList<T> getAll() throws SQLException;
}

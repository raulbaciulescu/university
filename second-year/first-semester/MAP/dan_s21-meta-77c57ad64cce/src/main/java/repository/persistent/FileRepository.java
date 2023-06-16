package repository.persistent;

import domain.model.Entity;
import domain.util.Serializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import repository.memory.Repository;

import java.io.*;
import java.sql.SQLException;
import java.util.Optional;

public class FileRepository<ID, T extends Entity<ID>> extends Repository<ID, T> {

    @NotNull private final String fileName;
    @NotNull protected final Serializer<T> serializer;

    public FileRepository(@NotNull final String fileName,
                          @NotNull final Serializer<T> serializer) {
        this.fileName = fileName;
        this.serializer = serializer;
        this.load();
    }

    private void load() {
        try (final FileReader fileReader = new FileReader(this.fileName);
             final BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final T entity = this.serializer.fromString(line);
                super.add(entity);
            }
        } catch (IOException | SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * @param entity the object which will be added
     * @return the result got from super.add(entity)
     */
    @NotNull
    @Override
    public Optional<T> add(@NotNull T entity) throws SQLException {
        final Optional<T> result = super.add(entity);
        if (result.isPresent()) {
            this.save(entity);
        }
        return result;
    }

    /**
     * @param entity the entity that has the same id with the one we want to update
     *               and new attributes
     * @return the result got from super.update(entity)
     */
    @NotNull
    @Override
    public Optional<T> update(@NotNull T entity) throws SQLException {
        final Optional<T> result = super.update(entity);
        if (result.isPresent()) {
            this.refreshFile();
        }
        return result;
    }

    /**
     * @param id the id of the entity that will be deleted
     * @return the result got from super.delete(id)
     */
    @NotNull
    @Override
    public Optional<T> delete(@NotNull ID id) throws SQLException {
        final Optional<T> result = super.delete(id);
        if (result.isPresent()) {
            this.refreshFile();
        }
        return result;
    }

    /**
     * it rewrites the entire file with the entities from the memory
     */
    private void refreshFile() {
        this.save(null);
    }


    /**
     * @param entity the entity that we want to persist
     *
     * if the entity is null, the entire file will be rewritten with the current entities
     *               from the memory, else, the entity will be appended inside the file
     */
    private void save(@Nullable final T entity) {
        final boolean append = entity != null;
        try (final FileWriter fileWriter = new FileWriter(this.fileName, append);
             final BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            if (append) {
                this.save(entity, bufferedWriter);
            } else {
                for (@NotNull final T object: this.getAll()) {
                    this.save(object, bufferedWriter);
                }
            }
        } catch (final IOException | SQLException exception) {
            exception.printStackTrace();
        }
    }


    /**
     * @param entity the entity we want to persist
     * @param bufferedWriter the bufferedWriter which gives access to write inside a file
     * @throws IOException if the write operation fail;
     */
    private void save(@NotNull final T entity,
                      @NotNull final BufferedWriter bufferedWriter) throws IOException {
        final String serializedEntity = this.serializer.toString(entity);
        bufferedWriter.write(serializedEntity);
    }
}

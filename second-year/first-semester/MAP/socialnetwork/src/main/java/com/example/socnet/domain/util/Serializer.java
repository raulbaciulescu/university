package com.example.socnet.domain.util;

import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public abstract class Serializer<T> {

    /**
     * @param line row line read from a file
     * @return an object formed with the attributes extracted from the line
     */
    @NotNull
    public abstract T fromString(@NotNull final String line) throws SQLException;

    /**
     * @param entity the object which we want to transform into a string for serialization
     * @return the string which contains the attributes of the entity that have to be serialized
     * and formatted as a line which is ready to be written in a file
     */
    @NotNull
    public abstract String toString(@NotNull final T entity);
}

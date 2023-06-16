package com.example.socnet.domain.validation;

import com.example.socnet.domain.exceptions.InvalidException;
import org.jetbrains.annotations.NotNull;

public interface Validator<T> {

    /**
     * @param object the object that has to be validated
     * @throws InvalidException if the object is invalid
     */
    void validate(@NotNull final T object) throws InvalidException;
}

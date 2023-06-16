package com.example.socnet.domain.validation;

import com.example.socnet.domain.exceptions.InvalidException;
import org.jetbrains.annotations.NotNull;

public class UsernameValidator implements Validator<String> {

    @Override
    public void validate(@NotNull final String username)
            throws InvalidException {
        if (username.isEmpty()) {
            throw new InvalidException("invalid username: empty");
        }
    }
}

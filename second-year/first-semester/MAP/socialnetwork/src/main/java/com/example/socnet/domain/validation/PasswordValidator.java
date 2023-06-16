package com.example.socnet.domain.validation;

import com.example.socnet.domain.exceptions.InvalidException;
import com.example.socnet.domain.util.Constants;
import org.jetbrains.annotations.NotNull;

public class PasswordValidator implements Validator<String> {

    @Override
    public void validate(@NotNull String password) throws InvalidException {
        final StringBuilder message = new StringBuilder();

        if (password.length() < Constants.Length.MIN_PASSWORD) {
            message.append("invalid password: too short!");
        }

        if (!message.isEmpty()) {
            throw new InvalidException(message.toString());
        }
    }
}

package domain.exceptions;

import org.jetbrains.annotations.NotNull;

public class InvalidException extends RuntimeException {

    public InvalidException(@NotNull final String message) {
        super(message);
    }
}

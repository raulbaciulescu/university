package domain.validation;

import domain.exceptions.InvalidException;
import domain.model.User;
import org.jetbrains.annotations.NotNull;

public class UserValidator implements Validator<User> {

    @Override
    public void validate(@NotNull final User user) throws InvalidException {
        final StringBuilder message = new StringBuilder();

        if (user.getId() == null) {
            message.append("invalid id -> value: null; ");
        }
        this.validateName(user.getFirstName(), message);
        this.validateName(user.getLastName(), message);

        if (!message.isEmpty()) {
            throw new InvalidException(message.toString());
        }
    }

    private void validateName(@NotNull final String name, @NotNull final StringBuilder message) {
        if (name.length() < 3) {
            message.append("invalid name -> value: ").append(name).append("; ");
        }
    }
}

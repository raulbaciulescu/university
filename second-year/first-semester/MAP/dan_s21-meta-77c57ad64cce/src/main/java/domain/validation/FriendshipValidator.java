package domain.validation;

import domain.exceptions.InvalidException;
import domain.model.Friendship;
import org.jetbrains.annotations.NotNull;

public class FriendshipValidator implements Validator<Friendship> {

    @Override
    public void validate(@NotNull final Friendship friendship) throws InvalidException {
        final StringBuilder message = new StringBuilder("");

        if (friendship.getId() == null) {
            message.append("invalid id -> value: null; ");
        }

        if (!message.isEmpty()) {
            throw new InvalidException(message.toString());
        }
    }
}

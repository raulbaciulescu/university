package socialnetwork.repository.file;

import socialnetwork.domain.Entity;
import socialnetwork.domain.Tuple;
import socialnetwork.domain.validators.Validator;
import socialnetwork.repository.memory.InMemoryRepository0;
import socialnetwork.repository.memory.InMemoryRepositoryFriendship;

public abstract class AbstractFileRepositoryFriendship<ID, E extends Entity<Tuple<ID, ID>>> extends InMemoryRepositoryFriendship<ID,E> {
    public AbstractFileRepositoryFriendship(Validator<E> validator) {
        super(validator);
    }
}
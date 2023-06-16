package socialnetwork.repository.file;

import socialnetwork.domain.Prietenie;
import socialnetwork.domain.Tuple;
import socialnetwork.domain.Utilizator;
import socialnetwork.domain.validators.Validator;

import java.util.List;

public class FriendshipFile extends AbstractFileRepositoryFriendship<Long, Prietenie>{
    public FriendshipFile(String fileName, Validator<Prietenie> validator) {
        super(fileName, validator);
    }

    @Override
    public Prietenie extractEntity(List<String> attributes) {

    }

    @Override
    protected String createEntityAsString(Utilizator entity) {
        return null;
    }

    @Override
    protected String createEntityAsString(Prietenie entity) {
        return null;
    }
}

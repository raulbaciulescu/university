package api;

import domain.User;

import java.util.Optional;

public interface UserRepository extends Repository<Long, User> {
    Optional<User> findUser(String username, String password);
}

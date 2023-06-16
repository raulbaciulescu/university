package com.example.socnet.service;


import com.example.socnet.domain.model.User;
import com.example.socnet.domain.model.UserLogin;
import com.example.socnet.domain.util.HashPasswordUtil;
import com.example.socnet.domain.validation.UserValidator;
import com.example.socnet.repository.Repository;
import com.example.socnet.repository.persistent.LoginRepository;
import org.jetbrains.annotations.NotNull;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class UserService {

    @NotNull private final UserValidator validator;
    @NotNull private final Repository<Long, User> repository;
    @NotNull private final LoginRepository loginRepository;
    @NotNull private final Random random;

    public UserService(@NotNull final UserValidator validator,
                       @NotNull final Repository<Long, User> repository,
                       @NotNull final LoginRepository loginRepository) {
        this.validator = validator;
        this.repository = repository;
        this.loginRepository = loginRepository;
        this.random = new Random();
    }

    /**
     * @param firstName first name of the user
     * @param lastName last name of the user
     * @return Optional.of(user) where user is the saved user,
     * or Optional.empty() if the adding operation failed
     */
    @NotNull
    public final Optional<User> add(@NotNull final String firstName,
                                    @NotNull final String lastName) throws SQLException {
        final long id = this.random.nextInt();
        final User user = this.createAndValidateUser(id, firstName, lastName);
        return this.repository.add(user);
    }

    /**
     * @param id id of the user we want to update
     * @param firstName (new) first name
     * @param lastName (new) last name
     * @return Optional.of(user) where user is the updated entity,
     * or Optional.empty() if update failed
     */
    @NotNull
    public final Optional<User> update(final long id,
                                       @NotNull final String firstName,
                                       @NotNull final String lastName) throws SQLException {
        final User user = this.createAndValidateUser(id, firstName, lastName);
        return this.repository.update(user);
    }

    /**
     * @param id id of the user
     * @param firstName first name of the user
     * @param lastName last name of the user
     * @return a new user with the attributes that have been passed
     * if the user passed validation
     */
    @NotNull
    private User createAndValidateUser(final long id,
                                       @NotNull final String firstName,
                                       @NotNull final String lastName) {
        final User user = new User(id, firstName, lastName);
        this.validator.validate(user);
        return user;
    }

    /**
     * @param id the id of the user we want to delete
     * @return Optional.of(user) where user is the deleted user,
     * or Optional.empty() if the process failed
     */
    @NotNull
    public final Optional<User> delete(final long id) throws SQLException {
        try {
            this.loginRepository.delete(id);
        } catch (SQLException ignored) {}
        return this.repository.delete(id);
    }

    /**
     * @param id id of a user
     * @return Optional.of(user) if the user has been found,
     * or Optional.empty() if not
     */
    @NotNull
    public final Optional<User> findByID(final long id) {
        try {
            return this.repository.findByID(id);
        } catch (SQLException ignored) {}
        return Optional.empty();
    }

    /**
     * @return a list which contains all the users in meta
     */
    @NotNull
    public final ArrayList<User> getAll() throws SQLException {
        return this.repository.getAll();
    }

    @NotNull
    public Optional<UserLogin> addLogin(final long user_id,
                                        @NotNull final String username,
                                        @NotNull final String password)
            throws SQLException, NoSuchAlgorithmException {
        final String hashedPassword = HashPasswordUtil.hash(password, username);
        final UserLogin login = new UserLogin(user_id, username, hashedPassword);
        return this.loginRepository.add(login);
    }

    @NotNull
    public Optional<UserLogin> findLogin(@NotNull final String username, @NotNull final String password)
            throws SQLException, NoSuchAlgorithmException {
        final String hashedPassword = HashPasswordUtil.hash(password, username);
        return this.loginRepository.find(username, hashedPassword);
    }
}

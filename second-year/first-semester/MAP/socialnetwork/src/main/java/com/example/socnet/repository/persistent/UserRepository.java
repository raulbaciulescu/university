package com.example.socnet.repository.persistent;

import com.example.socnet.domain.data.MetaDB;
import com.example.socnet.domain.data.table.UserTable;
import com.example.socnet.domain.model.User;
import com.example.socnet.repository.memory.Repository;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class UserRepository extends Repository<Long, User> {

    private final UserTable table;

    public UserRepository() throws SQLException {
        this.table = (UserTable) MetaDB.getInstance().table("users");
        for (final User user : this.table.getAll()) {
            super.add(user);
        }
    }


    @Override
    public @NotNull Optional<User> add(@NotNull final User user) throws SQLException {
        final Optional<User> result = super.add(user);
        if (result.isPresent()) {
            this.table.insert(user);
        }
        return result;
    }

    @Override
    public @NotNull Optional<User> findByID(@NotNull final Long id) throws SQLException {
        return this.table.whereID(id);
    }

    @Override
    public @NotNull Optional<User> update(@NotNull final User user) throws SQLException {
        final Optional<User> result = super.update(user);
        if (result.isPresent()) {
            this.table.update(user);
        }
        return result;
    }

    @Override
    public @NotNull Optional<User> delete(@NotNull final Long id) throws SQLException {
        final Optional<User> result = super.delete(id);
        if (result.isPresent()) {
            this.table.delete(id);
        }
        return result;
    }

    @Override
    public @NotNull ArrayList<User> getAll() throws SQLException {
        return this.table.getAll();
    }
}

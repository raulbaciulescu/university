package com.example.socnet.repository.persistent;

import com.example.socnet.domain.data.MetaDB;
import com.example.socnet.domain.data.table.LoginTable;
import com.example.socnet.domain.model.UserLogin;
import com.example.socnet.repository.memory.Repository;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.Optional;

public class LoginRepository extends Repository<Long, UserLogin> {

    final LoginTable table;

    public LoginRepository() throws SQLException {
        this.table = (LoginTable) MetaDB.getInstance().table("logins");
        for (final UserLogin login : this.table.getAll()) {
            super.add(login);
        }
    }

    @Override
    public @NotNull Optional<UserLogin> add(@NotNull UserLogin login) throws SQLException {
        final Optional<UserLogin> result = super.add(login);
        if (result.isPresent()) {
            this.table.insert(login);
        }
        return result;
    }

    @Override
    public @NotNull Optional<UserLogin> findByID(@NotNull Long id) throws SQLException {
        return this.table.whereID(id);
    }

    @Override
    public @NotNull Optional<UserLogin> delete(@NotNull Long id) throws SQLException {
        final Optional<UserLogin> result = super.delete(id);
        if (result.isPresent()) {
            this.table.delete(id);
        }
        return result;
    }

    @NotNull
    public final Optional<UserLogin> find(@NotNull final String username,
                                          @NotNull final String hashedPassword)
            throws SQLException {
        return this.table.find(username, hashedPassword);
    }
}

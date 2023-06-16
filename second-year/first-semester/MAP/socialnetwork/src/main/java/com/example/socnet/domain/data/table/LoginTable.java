package com.example.socnet.domain.data.table;

import com.example.socnet.domain.data.Database;
import com.example.socnet.domain.data.Table;
import com.example.socnet.domain.model.UserLogin;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class LoginTable implements Table<Long, UserLogin> {

    private final HashMap<Database.Query, PreparedStatement> statements;

    public LoginTable(@NotNull final Connection connection) throws SQLException {
        this.statements = new HashMap<>();
        this.initStatements(connection);
    }

    private void initStatements(@NotNull final Connection connection) throws SQLException {
        this.statements.put(Database.Query.ADD, connection
                .prepareStatement("insert into meta.public.login (user_id, username, password) values (?, ?, ?)"));
        this.statements.put(Database.Query.FIND_BY_ID, connection
                .prepareStatement("select * from meta.public.login where user_id = ?"));
        this.statements.put(Database.Query.DELETE, connection
                .prepareStatement("delete from meta.public.login where user_id = ?"));
        this.statements.put(Database.Query.GET_ALL, connection
                .prepareStatement("select * from meta.public.login"));
        this.statements.put(Database.Query.FIND, connection
                .prepareStatement("select * from meta.public.login where username = ? and password = ?"));
    }

    @Override
    public final void insert(@NotNull final UserLogin login)
            throws SQLException {
        @NotNull final PreparedStatement addStatement =
                this.statements.get(Database.Query.ADD);
        addStatement.setLong(1, login.getUserID());
        addStatement.setString(2, login.getUsername());
        addStatement.setString(3, login.getHashedPassword());
        addStatement.executeUpdate();
    }

    @Override
    public @NotNull final Optional<UserLogin> whereID(@NotNull final Long id)
            throws SQLException {
        @NotNull final PreparedStatement findStatement =
                this.statements.get(Database.Query.FIND_BY_ID);
        findStatement.setLong(1, id);
        final ResultSet resultSet = findStatement.executeQuery();
        return this.fromResultSet(resultSet);
    }

    @Override
    public @NotNull final Optional<UserLogin> fromResultSet(@NotNull final ResultSet resultSet)
            throws SQLException {
        if (!resultSet.next()) {
            return Optional.empty();
        }
        final long userID = resultSet.getLong(1);
        final String username = resultSet.getString(2);
        final String password = resultSet.getString(3);
        final UserLogin userLogin = new UserLogin(userID, username, password);
        return Optional.of(userLogin);
    }

    @Override
    public final void update(@NotNull final UserLogin object)
            throws SQLException {}

    @Override
    public void delete(@NotNull final Long id)
            throws SQLException {
        final PreparedStatement deleteStatement =
                this.statements.get(Database.Query.DELETE);
        deleteStatement.setLong(1, id);
        deleteStatement.executeUpdate();
    }

    @Override
    public @NotNull final ArrayList<UserLogin> getAll()
            throws SQLException {
        final ArrayList<UserLogin> logins = new ArrayList<>();
        final ResultSet resultSet = this.statements
                .get(Database.Query.GET_ALL).executeQuery();
        Optional<UserLogin> result = this.fromResultSet(resultSet);
        while (result.isPresent()) {
            logins.add(result.get());
            result = this.fromResultSet(resultSet);
        }
        return logins;
    }

    @NotNull
    public final Optional<UserLogin> find(@NotNull final String username,
                                          @NotNull final String hashedPassword)
            throws SQLException {
        @NotNull final PreparedStatement findStatement =
                this.statements.get(Database.Query.FIND);
        findStatement.setString(1, username);
        findStatement.setString(2, hashedPassword);
        final ResultSet resultSet = findStatement.executeQuery();
        return this.fromResultSet(resultSet);
    }
}

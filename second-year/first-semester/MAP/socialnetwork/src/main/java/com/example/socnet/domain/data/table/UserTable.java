package com.example.socnet.domain.data.table;

import com.example.socnet.domain.data.Database;
import com.example.socnet.domain.data.Table;
import com.example.socnet.domain.model.User;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class UserTable implements Table<Long, User> {

    private final HashMap<Database.Query, PreparedStatement> statements;

    public UserTable(@NotNull final Connection connection) throws SQLException {
        this.statements = new HashMap<>();
        this.initStatements(connection);
    }

    private void initStatements(@NotNull final Connection connection) throws SQLException {
        this.statements.put(Database.Query.ADD, connection
                .prepareStatement("insert into meta.public.user (id, first_name, last_name) values (?, ?, ?)"));
        this.statements.put(Database.Query.FIND_BY_ID, connection
                .prepareStatement("select * from meta.public.user where id = ?"));
        this.statements.put(Database.Query.UPDATE,connection
                .prepareStatement("update meta.public.user set first_name = ?, last_name = ? where id = ?"));
        this.statements.put(Database.Query.DELETE, connection
                .prepareStatement("delete from meta.public.user where id = ?"));
        this.statements.put(Database.Query.GET_ALL, connection
                .prepareStatement("select * from meta.public.user"));
    }

    @Override
    public final void insert(@NotNull final User user) throws SQLException {
        @NotNull final PreparedStatement addStatement =
                this.statements.get(Database.Query.ADD);
        addStatement.setLong(1, user.getId());
        addStatement.setString(2, user.getFirstName());
        addStatement.setString(3, user.getLastName());
        addStatement.executeUpdate();
    }

    @NotNull
    @Override
    public final Optional<User> whereID(@NotNull final Long id) throws SQLException {
        @NotNull final PreparedStatement findStatement =
                this.statements.get(Database.Query.FIND_BY_ID);
        findStatement.setLong(1, id);
        final ResultSet resultSet = findStatement.executeQuery();
        return this.fromResultSet(resultSet);
    }

    @Override
    public @NotNull Optional<User> fromResultSet(@NotNull ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return Optional.empty();
        }
        final long userID = resultSet.getLong(1);
        final String firstname = resultSet.getString(2);
        final String lastname = resultSet.getString(3);
        final User user = new User(userID, firstname, lastname);
        return Optional.of(user);
    }

    @Override
    public final void update(@NotNull final User user) throws SQLException {
        final PreparedStatement updateStatement =
                this.statements.get(Database.Query.UPDATE);
        updateStatement.setString(1, user.getFirstName());
        updateStatement.setString(2, user.getLastName());
        updateStatement.setLong(3, user.getId());
        updateStatement.executeUpdate();
    }

    @Override
    public final void delete(@NotNull final Long id) throws SQLException {
        final PreparedStatement deleteStatement =
                this.statements.get(Database.Query.DELETE);
        deleteStatement.setInt(1, Math.toIntExact(id));
        deleteStatement.executeUpdate();
    }

    @NotNull
    @Override
    public final ArrayList<User> getAll() throws SQLException {
        final ArrayList<User> users = new ArrayList<>();
        final ResultSet resultSet = this.statements
                .get(Database.Query.GET_ALL).executeQuery();
        Optional<User> result = this.fromResultSet(resultSet);
        while (result.isPresent()) {
            users.add(result.get());
            result = this.fromResultSet(resultSet);
        }
        return users;
    }
}

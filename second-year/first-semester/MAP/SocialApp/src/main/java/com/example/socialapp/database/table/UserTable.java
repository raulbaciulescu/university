package com.example.socialapp.database.table;

import com.example.socialapp.domain.User;
import com.example.socialapp.utils.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserTable implements Table<Long, User>{
    private Map<Query, PreparedStatement> statements;
    private Connection connection;

    public UserTable(Connection connection) throws SQLException {
        this.statements = new HashMap<>();
        this.connection = connection;
        initStatements();
    }

    public void initStatements() throws SQLException {
        statements.put(Query.ADD, connection.prepareStatement("INSERT INTO meta.public.user(id, first_name, last_name) VALUES (?, ?, ?)"));
        statements.put(Query.UPDATE, connection.prepareStatement("UPDATE meta.public.user set first_name = ?, last_name = ? WHERE id = ?"));
        statements.put(Query.DELETE, connection.prepareStatement("DELETE FROM meta.public.user WHERE id = ?"));
        statements.put(Query.GET_ALL, connection.prepareStatement("SELECT * FROM meta.public.user"));
        statements.put(Query.FIND_BY_ID, connection.prepareStatement("SELECT * FROM meta.public.user WHERE id = ?"));
    }


    @Override
    public void insert(User user) throws SQLException {
        PreparedStatement statement = statements.get(Query.ADD);
        statement.setLong(1, user.getId());
        statement.setString(2, user.getFirstName());
        statement.setString(3, user.getLastName());
        statement.executeUpdate();
    }

    @Override
    public Optional<User> findByID(Long id) throws SQLException {
        PreparedStatement statement = statements.get(Query.FIND_BY_ID);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            final long userID = resultSet.getLong(1);
            final String firstname = resultSet.getString(2);
            final String lastname = resultSet.getString(3);
            final User user = new User(userID, firstname, lastname);
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public void update(User user) throws SQLException {
        PreparedStatement statement = statements.get(Query.UPDATE);
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setLong(3, user.getId());
        statement.executeUpdate();
    }

    @Override
    public void delete(Long id) throws SQLException {
        PreparedStatement statement = statements.get(Query.DELETE);
        statement.setLong(1, id);
        statement.executeUpdate();
    }

    @Override
    public ArrayList<User> getAll() throws SQLException {
        final ArrayList<User> users = new ArrayList<>();
        ResultSet resultSet = statements.get(Query.GET_ALL).executeQuery();
        while (resultSet.next()) {
            final long userID = resultSet.getLong(1);
            final String firstname = resultSet.getString(2);
            final String lastname = resultSet.getString(3);
            final User user = new User(userID, firstname, lastname);
            users.add(user);
        }
        return users;
    }
}

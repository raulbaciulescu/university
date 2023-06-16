package com.example.socialapp.database.table;

import com.example.socialapp.domain.User;
import com.example.socialapp.domain.UserLogin;
import com.example.socialapp.utils.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LoginTable implements Table<Long, UserLogin> {
    private Map<Query, PreparedStatement> statements;
    private Connection connection;

    public LoginTable(Connection connection) throws SQLException {
        this.statements = new HashMap<>();
        this.connection = connection;
        initStatements();
    }

    public void initStatements() throws SQLException {
        statements.put(Query.ADD, connection.prepareStatement("INSERT INTO meta.public.login(user_id, username, password) VALUES (?, ?, ?)"));
        statements.put(Query.DELETE, connection.prepareStatement("DELETE FROM meta.public.login WHERE user_id = ?"));
        statements.put(Query.GET_ALL, connection.prepareStatement("SELECT * FROM meta.public.login"));
        statements.put(Query.FIND_BY_ID, connection.prepareStatement("SELECT * FROM meta.public.login WHERE user_id = ?"));
        statements.put(Query.FIND, connection.prepareStatement("SELECT * FROM meta.public.login WHERE username = ? AND password = ?"));
    }


    @Override
    public void insert(UserLogin userLogin) throws SQLException {
        PreparedStatement statement = statements.get(Query.ADD);
        statement.setLong(1, userLogin.getUserId());
        statement.setString(2, userLogin.getUsername());
        statement.setString(3, userLogin.getHashedPassword());
        statement.executeUpdate();
    }

    @Override
    public Optional<UserLogin> findByID(Long id) throws SQLException {
        PreparedStatement statement = statements.get(Query.FIND_BY_ID);
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            final long userID = resultSet.getLong(1);
            final String username = resultSet.getString(2);
            final String password = resultSet.getString(3);
            final UserLogin userLogin = new UserLogin(username, password, userID);
            return Optional.of(userLogin);
        }
        return Optional.empty();
    }

    public Optional<UserLogin> find(String username, String password) throws SQLException {
        PreparedStatement statement = statements.get(Query.FIND);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            final long userID = resultSet.getLong(1);
            final String username1 = resultSet.getString(2);
            final String password1 = resultSet.getString(3);
            final UserLogin userLogin = new UserLogin(username1, password1, userID);
            return Optional.of(userLogin);
        }
        return Optional.empty();
    }

    @Override
    public void update(UserLogin userLogin) throws SQLException {
        PreparedStatement statement = statements.get(Query.UPDATE);
        statement.setString(1, userLogin.getUsername());
        statement.setString(2, userLogin.getHashedPassword());
        statement.setLong(3, userLogin.getUserId());
        statement.executeUpdate();
    }

    @Override
    public void delete(Long id) throws SQLException {
        PreparedStatement statement = statements.get(Query.DELETE);
        statement.setLong(1, id);
        statement.executeUpdate();
    }

    @Override
    public ArrayList<UserLogin> getAll() throws SQLException {
        final ArrayList<UserLogin> userLogins = new ArrayList<>();
        ResultSet resultSet = statements.get(Query.GET_ALL).executeQuery();
        while (resultSet.next()) {
            final long userID = resultSet.getLong(1);
            final String username = resultSet.getString(2);
            final String password = resultSet.getString(3);
            final UserLogin userLogin = new UserLogin(username, password, userID);
            userLogins.add(userLogin);
        }
        return userLogins;
    }
}

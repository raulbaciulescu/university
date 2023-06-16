package com.example.socialapp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostgresConnector implements DatabaseConnector{
    private final List<Connection> connections;
    private final String databaseUrl;

    public PostgresConnector(String databaseUrl) {
        this.databaseUrl = databaseUrl;
        connections = new ArrayList<>();
    }


    @Override
    public Connection connect(String user, String password) throws SQLException {
        Connection connection = DriverManager.getConnection(databaseUrl, user, password);
        connections.add(connection);
        return connection;
    }

    @Override
    public void closeConnection(Connection connection) throws SQLException {
        if (!connection.isClosed() && connections.remove(connection))
            connection.close();
    }

    @Override
    public void close() throws Exception {
        for (Connection connection : connections) {
            connection.close();
        }
    }
}

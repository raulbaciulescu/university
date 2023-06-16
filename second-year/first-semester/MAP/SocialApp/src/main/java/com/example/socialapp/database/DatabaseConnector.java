package com.example.socialapp.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseConnector extends AutoCloseable{
    Connection connect(String user, String password) throws SQLException;
    void closeConnection(Connection connection) throws SQLException;
}
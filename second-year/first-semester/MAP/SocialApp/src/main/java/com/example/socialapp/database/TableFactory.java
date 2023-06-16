package com.example.socialapp.database;

import com.example.socialapp.database.table.FriendshipTable;
import com.example.socialapp.database.table.Table;
import com.example.socialapp.database.table.UserTable;
import com.example.socialapp.utils.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TableFactory {
    private static TableFactory instance = null;
    private PostgresConnector connector;
    private Map<Constants.Tables, Table> tables;
    Connection connection;

    private TableFactory() throws SQLException {
        tables = new HashMap<>();
        connector = new PostgresConnector(Constants.Db.DB_URL);
        connection = connector.connect(Constants.Db.DB_USER, Constants.Db.DB_PASSWORD);
        initTables();
    }

    private void initTables() throws SQLException {
        tables.put(Constants.Tables.USERS, new UserTable(connection));
        tables.put(Constants.Tables.FRIENDSHIPS, new FriendshipTable(connection));
    }


    public static TableFactory getInstance() throws SQLException {
        if (instance == null)
            instance = new TableFactory();
        return instance;
    }

    public Table<?, ?> table(final Constants.Tables tableName) {
        return tables.get(tableName);
    }
}

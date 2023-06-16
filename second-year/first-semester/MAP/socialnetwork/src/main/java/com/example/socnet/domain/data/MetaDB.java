package com.example.socnet.domain.data;

import com.example.socnet.domain.data.connection.PostgresDBConnector;
import com.example.socnet.domain.data.table.*;
import com.example.socnet.domain.util.Constants;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class MetaDB implements Database {

    private final PostgresDBConnector connector;
    private final HashMap<String, Table> tables;

    private MetaDB() {
        this.connector = new PostgresDBConnector(Constants.Postgres.META_DATABASE_URL);
        this.tables = new HashMap<>();
        try {
            final Connection connection
                    = this.connector.connectAs(
                            Constants.Postgres.MASTER_USER,
                            Constants.Postgres.MASTER_PASSWORD
                    );
            this.initTables(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initTables(@NotNull final Connection connection)
            throws SQLException {
        this.tables.put("users", new UserTable(connection));
        this.tables.put("chats", new ChatTable(connection));
        this.tables.put("messages", new MessageTable(connection));
        this.tables.put("requests", new RequestTable(connection));
        this.tables.put("friendships", new FriendshipTable(connection));
        this.tables.put("logins", new LoginTable(connection));
        this.tables.put("events", new EventTable(connection));
        this.tables.put("subscriptions", new EventSubscriptionTable(connection));
    }

    public static MetaDB getInstance() {
        return Instance.database;
    }

    @Override
    public void close() throws Exception {
        this.connector.close();
    }

    @NotNull
    @Override
    public Table<?, ?> table(@NotNull final String tableName) {
        return this.tables.get(tableName);
    }

    private static final class Instance {
        private static final MetaDB database = new MetaDB();
    }
}

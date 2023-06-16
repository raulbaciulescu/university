package domain.data.source.database;

import domain.data.source.database.connection.PostgresDBConnector;
import domain.data.source.database.table.FriendshipTable;
import domain.data.source.database.table.UserTable;
import domain.util.Constants;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class MetaDB implements Database {

    private final PostgresDBConnector connector;
    private final HashMap<String, Table> tables;

    private MetaDB() {
        this.connector = new PostgresDBConnector(Constants.POSTGRES_DATABASE_URL);
        this.tables = new HashMap<>();
        try {
            final Connection connection = this.connector
                    .connectAs(Constants.POSTGRES_MASTER_USER, Constants.POSTGRES_PASSWORD);
            this.tables.put("users", new UserTable(connection));
            this.tables.put("friendships", new FriendshipTable(connection));
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

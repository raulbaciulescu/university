package domain.data.source.database.connection;

import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostgresDBConnector implements DatabaseConnector {

    private final String databaseUrl;
    private final List<Connection> connections;

    public PostgresDBConnector(@NotNull final String databaseUrl) {
        this.databaseUrl = databaseUrl;
        this.connections = new ArrayList<>();
    }

    @NotNull
    public final Connection connectAs(@NotNull final String user,
                                               @NotNull final String password) throws SQLException {
        final Connection connection = DriverManager
                .getConnection(this.databaseUrl, user, password);
        this.connections.add(connection);
        return connection;
    }

    public final void closeConnection(@NotNull final Connection connection) throws SQLException {
        if (!connection.isClosed() && this.connections.remove(connection)) {
            connection.close();
        }
    }

    @Override
    public final void close() throws Exception {
        this.closeConnections();
    }

    private void closeConnections() throws SQLException {
        for (final Connection connection : this.connections) {
            if (!connection.isClosed()) {
                connection.close();
            }
        }
    }
}

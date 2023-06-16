package com.example.socnet.domain.data.connection;

import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseConnector extends AutoCloseable {

    @NotNull Connection connectAs(@NotNull final String user,
                                  @NotNull final String password) throws SQLException;

    void closeConnection(@NotNull final Connection connection) throws SQLException;
}

package com.example.socnet.domain.data.table;

import com.example.socnet.domain.data.Database;
import com.example.socnet.domain.data.Table;
import com.example.socnet.domain.model.Event;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class EventTable implements Table<Integer, Event> {

    private final HashMap<Database.Query, PreparedStatement> statements;

    public EventTable(@NotNull final Connection connection)
            throws SQLException {
        this.statements = new HashMap<>();
        this.initStatements(connection);
    }

    private void initStatements(@NotNull final Connection connection) throws SQLException {
        this.statements.put(Database.Query.ADD, connection
                .prepareStatement("insert into meta.public.event (name, timestamp) values (?, ?)"));
        this.statements.put(Database.Query.FIND_BY_ID, connection
                .prepareStatement("select * from meta.public.event where id = ?"));
        this.statements.put(Database.Query.DELETE, connection
                .prepareStatement("delete from meta.public.event where id = ?"));
        this.statements.put(Database.Query.GET_ALL, connection
                .prepareStatement("select * from meta.public.event"));
    }

    @Override
    public void insert(@NotNull final Event object) throws SQLException {
        @NotNull final PreparedStatement addStatement =
                this.statements.get(Database.Query.ADD);
        addStatement.setString(1, object.getName());
        addStatement.setString(2, object.getTimestamp().toString());
        addStatement.executeUpdate();
    }

    @Override
    public @NotNull Optional<Event> whereID(@NotNull final Integer id)
            throws SQLException {
        @NotNull final PreparedStatement findStatement =
                this.statements.get(Database.Query.FIND_BY_ID);
        findStatement.setInt(1, id);
        final ResultSet resultSet = findStatement.executeQuery();
        return this.fromResultSet(resultSet);
    }

    @Override
    public @NotNull Optional<Event> fromResultSet(@NotNull ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return Optional.empty();
        }
        final int id = resultSet.getInt(1);
        final String name = resultSet.getString(2);
        final LocalDateTime timestamp =
                LocalDateTime.parse(resultSet.getString(3));
        final Event event = new Event(id, name, timestamp);
        return Optional.of(event);
    }

    @Override
    public void update(@NotNull Event object) throws SQLException {}

    @Override
    public void delete(@NotNull Integer id) throws SQLException {
        final PreparedStatement deleteStatement =
                this.statements.get(Database.Query.DELETE);
        deleteStatement.setInt(1, id);
        deleteStatement.executeUpdate();
    }

    @Override
    public @NotNull ArrayList<Event> getAll() throws SQLException {
        final ArrayList<Event> events = new ArrayList<>();
        final ResultSet resultSet = this.statements
                .get(Database.Query.GET_ALL).executeQuery();
        Optional<Event> result = this.fromResultSet(resultSet);
        while (result.isPresent()) {
            events.add(result.get());
            result = this.fromResultSet(resultSet);
        }
        return events;
    }
}

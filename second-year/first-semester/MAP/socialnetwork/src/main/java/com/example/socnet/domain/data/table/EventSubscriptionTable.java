package com.example.socnet.domain.data.table;

import com.example.socnet.domain.data.Database;
import com.example.socnet.domain.data.Table;
import com.example.socnet.domain.model.EventSubscription;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class EventSubscriptionTable implements Table<Integer, EventSubscription> {

    private final HashMap<Database.Query, PreparedStatement> statements;

    public EventSubscriptionTable(@NotNull final Connection connection)
            throws SQLException {
        this.statements = new HashMap<>();
        this.initStatements(connection);
    }

    private void initStatements(@NotNull final Connection connection) throws SQLException {
        this.statements.put(Database.Query.ADD, connection
                .prepareStatement("insert into meta.public.event_subscription (event_id, user_id) values (?, ?)"));
        this.statements.put(Database.Query.FIND_BY_ID, connection
                .prepareStatement("select * from meta.public.event_subscription where id = ?"));
        this.statements.put(Database.Query.DELETE, connection
                .prepareStatement("delete from meta.public.event_subscription where id = ?"));
        this.statements.put(Database.Query.GET_ALL, connection
                .prepareStatement("select * from meta.public.event_subscription"));
    }

    @Override
    public void insert(@NotNull final EventSubscription object)
            throws SQLException {
        @NotNull final PreparedStatement addStatement =
                this.statements.get(Database.Query.ADD);
        addStatement.setInt(1, object.getEventId());
        addStatement.setLong(2, object.getUserId());
        addStatement.executeUpdate();
    }

    @Override
    public @NotNull Optional<EventSubscription> whereID(@NotNull final Integer id)
            throws SQLException {
        @NotNull final PreparedStatement findStatement =
                this.statements.get(Database.Query.FIND_BY_ID);
        findStatement.setInt(1, id);
        final ResultSet resultSet = findStatement.executeQuery();
        return this.fromResultSet(resultSet);
    }

    @Override
    public @NotNull Optional<EventSubscription> fromResultSet(@NotNull final ResultSet resultSet)
            throws SQLException {
        if (!resultSet.next()) {
            return Optional.empty();
        }
        final int id = resultSet.getInt(1);
        final int event_id = resultSet.getInt(2);
        final long user_id = resultSet.getLong(3);
        final EventSubscription eventSubscription =
                new EventSubscription(id, event_id, user_id);
        return Optional.of(eventSubscription);
    }

    @Override
    public void update(@NotNull EventSubscription object)
            throws SQLException {}

    @Override
    public void delete(@NotNull final Integer id)
            throws SQLException {
        final PreparedStatement deleteStatement =
                this.statements.get(Database.Query.DELETE);
        deleteStatement.setInt(1, id);
        deleteStatement.executeUpdate();
    }

    @Override
    public final @NotNull ArrayList<EventSubscription> getAll()
            throws SQLException {
        final ArrayList<EventSubscription> events = new ArrayList<>();
        final ResultSet resultSet = this.statements
                .get(Database.Query.GET_ALL).executeQuery();
        Optional<EventSubscription> result = this.fromResultSet(resultSet);
        while (result.isPresent()) {
            events.add(result.get());
            result = this.fromResultSet(resultSet);
        }
        return events;
    }
}

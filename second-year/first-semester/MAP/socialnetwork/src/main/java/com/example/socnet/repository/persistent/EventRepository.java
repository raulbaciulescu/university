package com.example.socnet.repository.persistent;

import com.example.socnet.domain.data.MetaDB;
import com.example.socnet.domain.data.table.EventTable;
import com.example.socnet.domain.model.Event;
import com.example.socnet.repository.memory.Repository;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class EventRepository extends Repository<Integer, Event> {

    private final EventTable table;

    public EventRepository() throws SQLException {
        this.table = (EventTable) MetaDB.getInstance().table("events");
        for (final Event event : this.table.getAll()) {
            super.add(event);
        }
    }

    @Override
    public @NotNull Optional<Event> add(@NotNull final Event event)
            throws SQLException {
        final Optional<Event> result = super.add(event);
        if (result.isPresent()) {
            this.table.insert(event);
        }
        return result;
    }

    @Override
    public @NotNull Optional<Event> findByID(@NotNull final Integer id)
            throws SQLException {
        return this.table.whereID(id);
    }

    @Override
    public @NotNull Optional<Event> delete(@NotNull Integer id)
            throws SQLException {
        final Optional<Event> result = super.delete(id);
        if (result.isPresent()) {
            this.table.delete(id);
        }
        return result;
    }

    @Override
    public @NotNull ArrayList<Event> getAll()
            throws SQLException {
        return this.table.getAll();
    }
}

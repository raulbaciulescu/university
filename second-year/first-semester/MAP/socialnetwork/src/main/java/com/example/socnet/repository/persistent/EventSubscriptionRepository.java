package com.example.socnet.repository.persistent;

import com.example.socnet.domain.data.MetaDB;
import com.example.socnet.domain.data.table.EventSubscriptionTable;
import com.example.socnet.domain.model.EventSubscription;
import com.example.socnet.repository.memory.Repository;
import com.example.socnet.service.CurrentUser;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventSubscriptionRepository extends Repository<Integer, EventSubscription> {

    private final EventSubscriptionTable table;

    public EventSubscriptionRepository() throws SQLException {
        this.table = (EventSubscriptionTable) MetaDB.getInstance().table("subscriptions");
        for (final EventSubscription eventSubscription : this.table.getAll()) {
            super.add(eventSubscription);
        }
    }

    @Override
    public @NotNull Optional<EventSubscription> add(@NotNull final EventSubscription entity)
            throws SQLException {
        final Optional<EventSubscription> result = super.add(entity);
        if (result.isPresent()) {
            this.table.insert(entity);
        }
        return result;
    }

    @Override
    public @NotNull Optional<EventSubscription> findByID(@NotNull final Integer id)
            throws SQLException {
        return this.table.whereID(id);
    }

    @Override
    public @NotNull Optional<EventSubscription> delete(@NotNull Integer id)
            throws SQLException {
        this.table.delete(id);
        return super.delete(id);
    }

    @Override
    public @NotNull ArrayList<EventSubscription> getAll()
            throws SQLException {
        final List<EventSubscription> events =
                this.table
                        .getAll()
                        .stream()
                        .filter(eventSubscription ->
                                eventSubscription.getUserId() ==
                                        CurrentUser.getInstance().getUser().getId())
                        .toList();
        return new ArrayList<>(events);
    }
}

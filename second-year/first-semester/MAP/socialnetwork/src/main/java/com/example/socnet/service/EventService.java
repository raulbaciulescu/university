package com.example.socnet.service;

import com.example.socnet.domain.model.Event;
import com.example.socnet.domain.model.EventSubscription;
import com.example.socnet.domain.model.SubscribedEvent;
import com.example.socnet.domain.util.Observable;
import com.example.socnet.repository.Repository;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public record EventService(
        Repository<Integer, Event> eventRepository,
        Repository<Integer, EventSubscription> eventSubscriptionRepository)
        implements Observable {

    public EventService(final @NotNull Repository<Integer, Event> eventRepository,
                        final @NotNull Repository<Integer, EventSubscription> eventSubscriptionRepository) {
        this.eventRepository = eventRepository;
        this.eventSubscriptionRepository = eventSubscriptionRepository;
    }

    public void add(@NotNull final String name, @NotNull final LocalDateTime timestamp)
            throws SQLException {
        final Event event = new Event(name, timestamp);
        eventRepository.add(event);
        this.update();
    }

    @NotNull
    public Optional<Event> findByID(final int id)
            throws SQLException {
        return eventRepository.findByID(id);
    }

    @NotNull
    public Optional<Event> delete(final int id)
            throws SQLException {
        final Optional<Event> result = eventRepository.delete(id);
        if (result.isPresent()) {
            this.update();
        }
        return result;
    }

    public void subscribe(final int eventId) throws SQLException {
        final EventSubscription eventSubscription =
                new EventSubscription(
                        eventId,
                        CurrentUser.getInstance().getUser().getId()
                );
        if (eventSubscriptionRepository.add(eventSubscription).isPresent()) {
            this.update();
        }
    }

    public void unsubscribe(final int subscriptionId)
            throws SQLException {
        eventSubscriptionRepository.delete(subscriptionId);
        this.update();
    }

    @NotNull
    public List<Event> getAll()
            throws SQLException {
        return this.eventRepository.getAll();
    }

    @NotNull
    public List<SubscribedEvent> getAllSubscribedEvents()
            throws SQLException {
        return eventSubscriptionRepository
                .getAll()
                .stream()
                .map(subscription -> {
                    try {
                        final Event event = eventRepository
                                .findByID(subscription.getEventId())
                                .get();
                        return new SubscribedEvent(event, subscription.getId());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .toList();
    }
}

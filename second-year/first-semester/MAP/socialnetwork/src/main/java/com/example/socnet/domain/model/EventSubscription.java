package com.example.socnet.domain.model;

public class EventSubscription extends Entity<Integer> {

    private final int eventId;
    private final long userId;

    public EventSubscription(final int eventId,
                             final long userId) {
        this.eventId = eventId;
        this.userId = userId;
    }

    public EventSubscription(final int id,
                             final int eventId,
                             final long userId) {
        this(eventId, userId);
        this.setId(id);
    }

    public final int getEventId() {
        return eventId;
    }

    public final long getUserId() {
        return userId;
    }
}

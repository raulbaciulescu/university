package com.example.socnet.domain.model;

public class SubscribedEvent {

    private final Event event;
    private final int subscriptionId;

    public SubscribedEvent(Event event, int subscriptionId) {
        this.event = event;
        this.subscriptionId = subscriptionId;
    }

    public Event getEvent() {
        return event;
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    @Override
    public String toString() {
        return event.toString();
    }
}

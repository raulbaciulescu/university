package com.example.socnet.domain.model;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

public class Event extends Entity<Integer> {

    private final String name;
    private final LocalDateTime timestamp;

    public Event(final int id,
                 final @NotNull String name,
                 final @NotNull LocalDateTime timestamp) {
        this(name, timestamp);
        this.setId(id);
    }

    public Event(final @NotNull String name,
                 final @NotNull LocalDateTime timestamp) {
        this.name = name;
        this.timestamp = timestamp;
    }

    public final @NotNull String getName() {
        return name;
    }

    public final @NotNull LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return name + "-" + timestamp;
    }
}

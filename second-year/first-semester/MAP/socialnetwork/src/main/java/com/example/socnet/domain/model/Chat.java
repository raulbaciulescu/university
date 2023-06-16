package com.example.socnet.domain.model;

public class Chat extends Entity<Integer> {

    private final String name;

    public Chat(final String name) {
        this.name = name;
    }

    public Chat(final int id, final String name) {
        this.setId(id);
        this.name = name;
    }

    public final String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

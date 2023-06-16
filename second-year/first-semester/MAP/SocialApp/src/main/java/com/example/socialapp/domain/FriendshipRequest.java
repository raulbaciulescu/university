package com.example.socialapp.domain;

public class FriendshipRequest extends Entity<Integer> {

    public enum Status {
        PENDING,
        ACCEPTED,
        REJECTED,
    }

    private final long senderID;
    private final long citedID;
    private Status status;

    public FriendshipRequest(final int id, final long senderID, final long citedID, final Status status) {
        this(senderID, citedID, status);
        this.setId(id);
    }

    public FriendshipRequest(final long senderID, final long citedID, final Status status) {
        this.senderID = senderID;
        this.citedID = citedID;
        this.status = status;
    }

    public final long getSenderID() {
        return this.senderID;
    }

    public final long getCitedID() {
        return this.citedID;
    }

    public final void accept() {
        this.status = Status.ACCEPTED;
    }

    public final void reject() {
        this.status = Status.REJECTED;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return this.getId() + " ---- " + this.senderID + " -> " + this.citedID + " status: " + this.status;
    }
}

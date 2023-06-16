package com.example.socnet.domain.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Message extends Entity<Integer> {

    private final long senderID;
    private final int chatID;
    private final String body;
    private final LocalDateTime timestamp;
    @Nullable private final Integer replyID;
    private Message replayOn;

    public Message(final long senderID,
                   final int chatID,
                   final String body,
                   @Nullable final Integer replyID) {
        this.senderID = senderID;
        this.chatID = chatID;
        this.body = body;
        this.timestamp = LocalDateTime.now();
        this.replyID = replyID;
    }

    public Message(final int id,
                   final long senderID,
                   final int chatID,
                   final String body,
                   final LocalDateTime timestamp,
                   @Nullable final Integer replyID) {
        this.setId(id);
        this.senderID = senderID;
        this.chatID = chatID;
        this.body = body;
        this.timestamp = timestamp;
        this.replyID = replyID;
    }

    public final long getSenderID() {
        return senderID;
    }

    public final int getChatID() {
        return chatID;
    }

    public final String getBody() {
        return body;
    }

    public final LocalDateTime getTimestamp() {
        return timestamp;
    }

    public final long getTimestampMillis() {
        return Date.from(timestamp.atZone(
                ZoneId.systemDefault()).toInstant()).getTime();
    }

    @Nullable
    public final Integer getReplyID() {
        return replyID;
    }

    @Override
    public String toString() {
        return this.body + (this.replyID != null ? "\nreplied to: " + this.replayOn.body : "");
    }

    public final void setReplayOn(@NotNull final Message replayOn) {
        this.replayOn = replayOn;
    }
}

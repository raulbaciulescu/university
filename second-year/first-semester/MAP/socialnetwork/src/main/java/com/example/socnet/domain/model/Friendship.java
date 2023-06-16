package com.example.socnet.domain.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Friendship extends Entity<Long> {

    @NotNull private final LocalDateTime creationDate;
    @NotNull private final Tuple<User, User> users;

    public Friendship(@NotNull final LocalDateTime creationDate,
                      @NotNull final Tuple<User, User> users) {
        this(null, creationDate, users);
    }

    public Friendship(@Nullable final Long id,
                      @NotNull final LocalDateTime creationDate,
                      @NotNull final Tuple<User, User> users) {
        if (id != null) {
            this.setId(id);
        }
        this.creationDate = creationDate;
        this.users = users;
    }

    @NotNull
    public final LocalDateTime getCreationDate() {
        return this.creationDate;
    }

    public final long getCreationDateMillis() {
        return Date.from(creationDate.atZone(
                ZoneId.systemDefault()).toInstant()).getTime();
    }

    @NotNull
    public final Tuple<User, User> getUsers() {
        return this.users;
    }

    @Override
    public String toString() {
        final long id = this.users.left().getId() + this.users.right().getId();
        return "Fs(" + id + ") " + this.creationDate + ": (" + this.users.left() + " <-> " + this.users.right() + ");";
    }
}

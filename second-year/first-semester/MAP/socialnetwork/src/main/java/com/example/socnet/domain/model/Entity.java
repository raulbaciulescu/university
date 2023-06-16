package com.example.socnet.domain.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

public abstract class Entity<ID> implements Serializable {

    @Nullable private ID id;

    @Nullable
    public final ID getId() {
        return id;
    }

    public final void setId(@NotNull final ID id) {
        this.id = id;
    }
}

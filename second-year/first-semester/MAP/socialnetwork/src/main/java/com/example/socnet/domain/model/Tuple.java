package com.example.socnet.domain.model;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public record Tuple<T1, T2>(@NotNull T1 left, @NotNull T2 right) implements Serializable {
}

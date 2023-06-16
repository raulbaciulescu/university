package domain.model;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public final record Tuple<T1, T2>(@NotNull T1 left, @NotNull T2 right) implements Serializable {
}

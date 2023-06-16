package domain.data.source.database.table;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

public record FriendshipDTO(@NotNull LocalDateTime creationDate, long firstID, long secondID) {}

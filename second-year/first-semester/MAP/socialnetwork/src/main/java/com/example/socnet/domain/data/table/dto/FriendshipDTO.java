package com.example.socnet.domain.data.table.dto;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

public record FriendshipDTO(@NotNull LocalDateTime creationDate, long firstID, long secondID) {}

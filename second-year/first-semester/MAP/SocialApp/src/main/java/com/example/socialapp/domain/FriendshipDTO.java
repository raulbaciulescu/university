package com.example.socialapp.domain;
import java.time.LocalDateTime;

public record FriendshipDTO(LocalDateTime creationDate, long firstID, long secondID) {}
package com.example.socnet.super_service;

import com.example.socnet.domain.model.Message;
import com.example.socnet.service.ChatService;
import com.example.socnet.service.MessageService;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public record ChatSuperService(@NotNull ChatService chatService,
                               @NotNull MessageService messageService) {

    @NotNull
    public ArrayList<Message> getChatMessages(final int chatID) throws SQLException {
        return  (ArrayList<Message>) this.messageService.getAll(false)
                .stream()
                .filter(message -> chatID == message.getChatID())
                .collect(Collectors.toList());
    }
}

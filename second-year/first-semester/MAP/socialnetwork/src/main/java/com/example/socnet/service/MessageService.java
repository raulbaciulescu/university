package com.example.socnet.service;

import com.example.socnet.domain.model.Message;
import com.example.socnet.domain.util.Observable;
import com.example.socnet.repository.Repository;
import com.example.socnet.repository.persistent.MessageRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class MessageService
        implements Observable {

    @NotNull
    private final Repository<Integer, Message> messageRepository;

    private int page;

    public MessageService(@NotNull Repository<Integer, Message> messageRepository) {
        this.messageRepository = messageRepository;
        this.page = 1;
    }

    public void add(final long senderID,
                    final int chatID,
                    @NotNull final String body)
            throws SQLException {
        this.add(senderID, chatID, body, null);
    }

    public void add(final long senderID,
                    final int chatID,
                    @NotNull final String body,
                    @Nullable final Integer replyID)
            throws SQLException {
        final Message message = new Message(senderID, chatID, body, replyID);
        this.messageRepository.add(message);
        this.update();
    }

    @NotNull
    public Optional<Message> findByID(final int id) throws SQLException {
        return this.messageRepository.findByID(id);
    }

    public void update(final int id, @NotNull final String body)
            throws SQLException {
        final Optional<Message> oldMessage = this.findByID(id);
        if (oldMessage.isPresent()) {
            final Message message = new Message(
                    id,
                    oldMessage.get().getSenderID(),
                    oldMessage.get().getChatID(),
                    body,
                    LocalDateTime.now(),
                    oldMessage.get().getReplyID());
            this.messageRepository.update(message);
            this.update();
        }
    }

    public void delete(final int id) throws SQLException {
        this.messageRepository.delete(id);
        this.update();
    }

    @NotNull
    public final List<Message> getAll(final boolean readMore) throws SQLException {
        return ((MessageRepository) this.messageRepository)
                .getAll(readMore ? ++page : page);
    }

    @NotNull
    public final List<Message> getAll(final long from,
                                      final long to)
            throws SQLException {
        return messageRepository
                .getAll()
                .stream()
                .filter(message -> {
                    final long timestamp = message.getTimestampMillis();
                    return from <= timestamp && timestamp <= to;
                })
                .toList();
    }
}

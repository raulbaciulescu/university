package com.example.socnet.service;

import com.example.socnet.domain.model.Chat;
import com.example.socnet.repository.Repository;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public record ChatService(@NotNull Repository<Integer, Chat> chatRepository) {

    @NotNull
    public Optional<Chat> add(final String name) throws SQLException {
        final Chat chat = new Chat(name);
        return this.chatRepository.add(chat);
    }

    @NotNull
    public Optional<Chat> update(final int id,
                                 @NotNull final String name)
            throws SQLException {
        final Chat chat = new Chat(id, name);
        return this.chatRepository.update(chat);
    }

    @NotNull
    public Optional<Chat> delete(final int id) throws SQLException {
        return this.chatRepository.delete(id);
    }

    @NotNull
    public Optional<Chat> findByID(final int id) throws SQLException {
        return this.chatRepository.findByID(id);
    }

    @NotNull
    public ArrayList<Chat> getAll() throws SQLException {
        return this.chatRepository.getAll();
    }
}

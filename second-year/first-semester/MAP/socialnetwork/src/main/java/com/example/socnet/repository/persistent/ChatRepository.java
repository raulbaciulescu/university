package com.example.socnet.repository.persistent;

import com.example.socnet.domain.data.MetaDB;
import com.example.socnet.domain.data.table.ChatTable;
import com.example.socnet.domain.model.Chat;
import com.example.socnet.repository.memory.Repository;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ChatRepository extends Repository<Integer, Chat> {

    private final ChatTable table;

    public ChatRepository() throws SQLException {
        this.table = (ChatTable) MetaDB.getInstance().table("chats");
        for (final Chat chat : this.table.getAll()) {
            super.add(chat);
        }
    }

    @Override
    public @NotNull Optional<Chat> add(@NotNull final Chat chat) throws SQLException {
        final Optional<Chat> result = super.add(chat);
        if (result.isPresent()) {
            this.table.insert(chat);
        }
        return result;
    }

    @Override
    public @NotNull Optional<Chat> update(@NotNull final Chat chat) throws SQLException {
        final Optional<Chat> result = super.update(chat);
        if (result.isPresent()) {
            this.table.update(chat);
        }
        return result;
    }

    @Override
    public @NotNull Optional<Chat> findByID(@NotNull final Integer id) throws SQLException {
        return this.table.whereID(id);
    }

    @Override
    public @NotNull Optional<Chat> delete(@NotNull final Integer id) throws SQLException {
        final Optional<Chat> result = super.delete(id);
        if (result.isPresent()) {
            this.table.delete(id);
        }
        return result;
    }

    @Override
    public @NotNull ArrayList<Chat> getAll() throws SQLException {
        return this.table.getAll();
    }
}

package com.example.socnet.repository.persistent;

import com.example.socnet.domain.data.MetaDB;
import com.example.socnet.domain.data.table.MessageTable;
import com.example.socnet.domain.model.Message;
import com.example.socnet.domain.util.Constants;
import com.example.socnet.repository.memory.Repository;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessageRepository extends Repository<Integer, Message> {

    private final MessageTable table;

    public MessageRepository() {
        this.table = (MessageTable) MetaDB.getInstance().table("messages");
    }

    @Override
    public @NotNull Optional<Message> add(@NotNull final Message message) throws SQLException {
        final Optional<Message> result = super.add(message);
        if (result.isPresent()) {
            this.table.insert(message);
        }
        return result;
    }

    @Override
    public @NotNull Optional<Message> update(@NotNull final Message message) throws SQLException {
        final Optional<Message> result = super.update(message);
        if (result.isPresent()) {
            this.table.update(message);
        }
        return result;
    }

    @Override
    public @NotNull Optional<Message> findByID(@NotNull final Integer id) throws SQLException {
        return this.table.whereID(id);
    }

    @Override
    public @NotNull Optional<Message> delete(@NotNull final Integer id) throws SQLException {
        final Optional<Message> result = super.delete(id);
        if (result.isPresent()) {
            this.table.delete(id);
        }
        return result;
    }

    @Override
    public final @NotNull ArrayList<Message> getAll()
            throws SQLException {
        return this.table.getAll();
    }

    @NotNull
    public final List<Message> getAll(final int page)
            throws SQLException {
        final List<Message> messages = this.table.getAll();
        final int startIndex =
                messages.size() - page * Constants.Length.PAGE;
        if (startIndex < 0) {
            return messages;
        }
        return messages.subList(
                messages.size() - page * Constants.Length.PAGE,
                messages.size()
        );
    }
}

package com.example.socnet.domain.data.table;

import com.example.socnet.domain.data.Database;
import com.example.socnet.domain.data.Table;
import com.example.socnet.domain.model.Message;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class MessageTable implements Table<Integer, Message> {

    private final HashMap<Database.Query, PreparedStatement> statements;

    public MessageTable(@NotNull final Connection connection) throws SQLException {
        this.statements = new HashMap<>();
        this.initStatements(connection);
    }

    private void initStatements(@NotNull final Connection connection) throws SQLException {
        this.statements.put(Database.Query.ADD, connection
                .prepareStatement("insert into meta.public.message (sender_id, chat_id, body, timestamp, reply_id) values (?, ?, ?, ?, ?)"));
        this.statements.put(Database.Query.ADD2, connection
                .prepareStatement("insert into meta.public.message (sender_id, chat_id, body, timestamp) values (?, ?, ?, ?)"));
        this.statements.put(Database.Query.FIND_BY_ID, connection
                .prepareStatement("select * from meta.public.message where id = ?"));
        this.statements.put(Database.Query.DELETE, connection
                .prepareStatement("delete from meta.public.message where id = ?"));
        this.statements.put(Database.Query.GET_ALL, connection
                .prepareStatement("select * from meta.public.message order by timestamp asc"));
    }

    @Override
    public final void insert(@NotNull final Message message) throws SQLException {
        @NotNull final PreparedStatement addStatement =
                message.getReplyID() == null
                        ? this.statements.get(Database.Query.ADD2)
                        : this.statements.get(Database.Query.ADD);
        addStatement.setLong(1, message.getSenderID());
        addStatement.setInt(2, message.getChatID());
        addStatement.setString(3, message.getBody());
        addStatement.setString(4, message.getTimestamp().toString());
        if (message.getReplyID() != null) {
            addStatement.setInt(5, message.getReplyID());
        }
        addStatement.executeUpdate();
    }

    @Override
    public @NotNull Optional<Message> whereID(@NotNull Integer id) throws SQLException {
        @NotNull final PreparedStatement findStatement =
                this.statements.get(Database.Query.FIND_BY_ID);
        findStatement.setInt(1, id);
        final ResultSet resultSet = findStatement.executeQuery();
        return this.fromResultSet(resultSet);
    }

    @Override
    public @NotNull Optional<Message> fromResultSet(@NotNull final ResultSet resultSet)
            throws SQLException {
        if (!resultSet.next()) {
            return Optional.empty();
        }
        final int id = resultSet.getInt(1);
        final long senderID = resultSet.getLong(2);
        final int chatID = resultSet.getInt(3);
        final String body = resultSet.getString(4);
        final LocalDateTime timestamp = LocalDateTime
                .parse(resultSet.getString(5));
        @Nullable Integer replyID = null;
        try {
            replyID = Integer.parseInt(resultSet.getString(6));
        } catch (Exception ignored) {}
        final Message message = new Message(id,
                senderID,
                chatID,
                body,
                timestamp,
                replyID
        );
        return Optional.of(message);
    }

    @Override
    public final void update(@NotNull final Message message) throws SQLException {}

    @Override
    public void delete(@NotNull final Integer id) throws SQLException {
        final PreparedStatement deleteStatement =
                this.statements.get(Database.Query.DELETE);
        deleteStatement.setInt(1, id);
        deleteStatement.executeUpdate();
    }

    @Override
    public @NotNull ArrayList<Message> getAll() throws SQLException {
        final ArrayList<Message> messages = new ArrayList<>();
        final ResultSet resultSet = this.statements
                .get(Database.Query.GET_ALL).executeQuery();
        Optional<Message> result = this.fromResultSet(resultSet);
        while (result.isPresent()) {
            final Message message = result.get();
            this.attachReplyOnIfNeeded(messages, message);
            messages.add(message);
            result = this.fromResultSet(resultSet);
        }
        return messages;
    }

    private void attachReplyOnIfNeeded(@NotNull final List<Message> source,
                                       @NotNull final Message message) {
        if (message.getReplyID() != null) {
            for (final Message messageFromList : source) {
                if (message.getReplyID().equals(messageFromList.getId())) {
                    message.setReplayOn(messageFromList);
                }
            }
        }
    }
}

package com.example.socnet.domain.data.table;

import com.example.socnet.domain.data.Database;
import com.example.socnet.domain.data.Table;
import com.example.socnet.domain.model.Chat;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class ChatTable implements Table<Integer, Chat> {

    private final HashMap<Database.Query, PreparedStatement> statements;

    public ChatTable(@NotNull final Connection connection) throws SQLException {
        this.statements = new HashMap<>();
        this.initStatements(connection);
    }

    private void initStatements(@NotNull final Connection connection) throws SQLException {
        this.statements.put(Database.Query.ADD, connection
                .prepareStatement("insert into meta.public.chat (name) values (?)"));
        this.statements.put(Database.Query.FIND_BY_ID, connection
                .prepareStatement("select * from meta.public.chat where id = ?"));
        this.statements.put(Database.Query.UPDATE,connection
                .prepareStatement("update meta.public.chat set name = ? where id = ?"));
        this.statements.put(Database.Query.DELETE, connection
                .prepareStatement("delete from meta.public.chat where id = ?"));
        this.statements.put(Database.Query.GET_ALL, connection
                .prepareStatement("select * from meta.public.chat"));
    }

    @Override
    public void insert(@NotNull final Chat chat) throws SQLException {
        @NotNull final PreparedStatement addStatement =
                this.statements.get(Database.Query.ADD);
        addStatement.setString(1, chat.getName());
        addStatement.executeUpdate();
    }

    @NotNull
    @Override
    public final Optional<Chat> whereID(@NotNull final Integer id) throws SQLException {
        @NotNull final PreparedStatement findStatement =
                this.statements.get(Database.Query.FIND_BY_ID);
        findStatement.setInt(1, id);
        final ResultSet resultSet = findStatement.executeQuery();
        return this.fromResultSet(resultSet);
    }

    @NotNull
    @Override
    public final Optional<Chat> fromResultSet(@NotNull final ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return Optional.empty();
        }
        final int id = resultSet.getInt(1);
        final String name = resultSet.getString(2);
        final Chat chat = new Chat(id, name);
        return Optional.of(chat);
    }

    @Override
    public final void update(@NotNull final Chat chat) throws SQLException {
        @NotNull final PreparedStatement updateStatement =
                this.statements.get(Database.Query.UPDATE);

        updateStatement.setString(1, chat.getName());
        updateStatement.setInt(2, chat.getId());
        updateStatement.executeUpdate();
    }

    @Override
    public final void delete(@NotNull final Integer id) throws SQLException {
        @NotNull final PreparedStatement deleteStatement =
                this.statements.get(Database.Query.DELETE);

        deleteStatement.setInt(1, id);
        deleteStatement.executeUpdate();
    }

    @NotNull
    @Override
    public final ArrayList<Chat> getAll() throws SQLException {
        final ArrayList<Chat> chats = new ArrayList<>();
        final ResultSet resultSet = this.statements
                .get(Database.Query.GET_ALL).executeQuery();
        Optional<Chat> result = this.fromResultSet(resultSet);
        while (result.isPresent()) {
            chats.add(result.get());
            result = this.fromResultSet(resultSet);
        }
        return chats;
    }
}

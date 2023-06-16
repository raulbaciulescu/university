package com.example.socnet.domain.data.table;

import com.example.socnet.domain.data.Database;
import com.example.socnet.domain.data.Table;
import com.example.socnet.domain.model.FriendshipRequest;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class RequestTable implements Table<Integer, FriendshipRequest> {

    private final HashMap<Database.Query, PreparedStatement> statements;

    public RequestTable(@NotNull final Connection connection) throws SQLException {
        this.statements = new HashMap<>();
        this.initStatements(connection);
    }

    private void initStatements(@NotNull final Connection connection) throws SQLException {
        this.statements.put(Database.Query.ADD, connection
                .prepareStatement("insert into meta.public.friendship_request (sender_id, cited_id, status) values (?, ?, ?)"));
        this.statements.put(Database.Query.FIND_BY_ID, connection
                .prepareStatement("select * from meta.public.friendship_request where id = ?"));
        this.statements.put(Database.Query.UPDATE,connection
                .prepareStatement("update meta.public.friendship_request set status = ? where id = ?"));
        this.statements.put(Database.Query.DELETE, connection
                .prepareStatement("delete from meta.public.friendship_request where id = ?"));
        this.statements.put(Database.Query.GET_ALL, connection
                .prepareStatement("select * from meta.public.friendship_request"));
    }


    @Override
    public void insert(@NotNull FriendshipRequest request) throws SQLException {
        @NotNull final PreparedStatement addStatement =
                this.statements.get(Database.Query.ADD);
        addStatement.setLong(1, request.getSenderID());
        addStatement.setLong(2, request.getCitedID());
        addStatement.setInt(3, request.getStatus().ordinal());
        addStatement.executeUpdate();
    }

    @Override
    public @NotNull Optional<FriendshipRequest> whereID(@NotNull Integer id) throws SQLException {
        @NotNull final PreparedStatement findStatement =
                this.statements.get(Database.Query.FIND_BY_ID);
        findStatement.setLong(1, id);
        final ResultSet resultSet = findStatement.executeQuery();
        return this.fromResultSet(resultSet);
    }

    @Override
    public @NotNull Optional<FriendshipRequest> fromResultSet(@NotNull ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return Optional.empty();
        }
        final int id = resultSet.getInt(1);
        final long senderID = resultSet.getLong(2);
        final long citedID = resultSet.getLong(3);
        final FriendshipRequest.Status status = switch (resultSet.getInt(4)) {
            case 0 -> FriendshipRequest.Status.PENDING;
            case 1 -> FriendshipRequest.Status.ACCEPTED;
            default -> FriendshipRequest.Status.REJECTED;
        };
        final FriendshipRequest request = new FriendshipRequest(id, senderID, citedID, status);
        return Optional.of(request);
    }

    @Override
    public void update(@NotNull FriendshipRequest request) throws SQLException {
        final PreparedStatement updateStatement =
                this.statements.get(Database.Query.UPDATE);
        updateStatement.setInt(1, request.getStatus().ordinal());
        updateStatement.setInt(2, request.getId());
        updateStatement.executeUpdate();
    }

    @Override
    public void delete(@NotNull Integer id) throws SQLException {
        final PreparedStatement deleteStatement =
                this.statements.get(Database.Query.DELETE);
        deleteStatement.setInt(1, id);
        deleteStatement.executeUpdate();
    }

    @NotNull
    @Override
    public final ArrayList<FriendshipRequest> getAll() throws SQLException {
        final ArrayList<FriendshipRequest> requests = new ArrayList<>();
        final ResultSet resultSet = this.statements
                .get(Database.Query.GET_ALL).executeQuery();
        Optional<FriendshipRequest> result = this.fromResultSet(resultSet);
        while (result.isPresent()) {
            requests.add(result.get());
            result = this.fromResultSet(resultSet);
        }
        return requests;
    }
}

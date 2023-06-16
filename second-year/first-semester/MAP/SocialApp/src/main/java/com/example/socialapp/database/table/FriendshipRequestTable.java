package com.example.socialapp.database.table;

import com.example.socialapp.domain.FriendshipRequest;
import com.example.socialapp.utils.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FriendshipRequestTable implements Table<Integer, FriendshipRequest> {
    private final Map<Query, PreparedStatement> statements;

    public FriendshipRequestTable(Connection connection) throws SQLException {
        this.statements = new HashMap<>();
        this.initStatements(connection);
    }

    private void initStatements(Connection connection) throws SQLException {
        this.statements.put(Query.ADD, connection
                .prepareStatement("insert into meta.public.friendship_request (sender_id, cited_id, status) values (?, ?, ?)"));
        this.statements.put(Query.FIND_BY_ID, connection
                .prepareStatement("select * from meta.public.friendship_request where id = ?"));
        this.statements.put(Query.UPDATE,connection
                .prepareStatement("update meta.public.friendship_request set status = ? where id = ?"));
        this.statements.put(Query.DELETE, connection
                .prepareStatement("delete from meta.public.friendship_request where id = ?"));
        this.statements.put(Query.GET_ALL, connection
                .prepareStatement("select * from meta.public.friendship_request"));
    }


    @Override
    public void insert(FriendshipRequest request) throws SQLException {
        final PreparedStatement addStatement = this.statements.get(Query.ADD);
        addStatement.setLong(1, request.getSenderID());
        addStatement.setLong(2, request.getCitedID());
        addStatement.setInt(3, request.getStatus().ordinal());
        addStatement.executeUpdate();
    }

    @Override
    public Optional<FriendshipRequest> findByID(Integer id) throws SQLException {
        final PreparedStatement findStatement = this.statements.get(Query.FIND_BY_ID);
        findStatement.setLong(1, id);
        final ResultSet resultSet = findStatement.executeQuery();
        return this.fromResultSet(resultSet);
    }


    public Optional<FriendshipRequest> fromResultSet(ResultSet resultSet) throws SQLException {
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
    public void update(FriendshipRequest request) throws SQLException {
        final PreparedStatement updateStatement =
                this.statements.get(Query.UPDATE);
        updateStatement.setInt(1, request.getStatus().ordinal());
        updateStatement.setInt(2, request.getId());
        updateStatement.executeUpdate();
    }

    @Override
    public void delete(Integer id) throws SQLException {
        final PreparedStatement deleteStatement =
                this.statements.get(Query.DELETE);
        deleteStatement.setInt(1, id);
        deleteStatement.executeUpdate();
    }

    @Override
    public final ArrayList<FriendshipRequest> getAll() throws SQLException {
        final ArrayList<FriendshipRequest> requests = new ArrayList<>();
        final ResultSet resultSet = this.statements.get(Query.GET_ALL).executeQuery();
        Optional<FriendshipRequest> result = this.fromResultSet(resultSet);
        while (result.isPresent()) {
            requests.add(result.get());
            result = this.fromResultSet(resultSet);
        }
        return requests;
    }

}

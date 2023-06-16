package com.example.socialapp.database.table;

import com.example.socialapp.domain.FriendshipDTO;
import com.example.socialapp.domain.Tuple;
import com.example.socialapp.utils.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FriendshipTable implements Table<Tuple<Long, Long>, FriendshipDTO> {
    private Connection connection;
    private Map<Query, PreparedStatement> statements;

    public FriendshipTable(Connection connection) throws SQLException {
        this.connection = connection;
        statements = new HashMap<>();
        initStatements();
    }

    private void initStatements() throws SQLException {
        statements.put(Query.ADD, connection.prepareStatement("INSERT INTO friendship(first_id, second_id, creation_date) VALUES (?, ?, ?)"));
        statements.put(Query.FIND_BY_ID, connection.prepareStatement("SELECT * FROM friendship WHERE first_id = ? AND second_id = ?"));
        statements.put(Query.DELETE, connection.prepareStatement("DELETE FROM friendship WHERE first_id = ? AND second_id = ?"));
        statements.put(Query.GET_ALL, connection.prepareStatement("SELECT * FROM friendship"));
    }

    @Override
    public void insert(FriendshipDTO friendshipDTO) throws SQLException {
        PreparedStatement statement = statements.get(Query.ADD);
        statement.setLong(1, friendshipDTO.firstID());
        statement.setLong(2, friendshipDTO.secondID());
        statement.setString(3, friendshipDTO.creationDate().toString());
    }

    @Override
    public Optional<FriendshipDTO> findByID(Tuple<Long, Long> tuple) throws SQLException {
        PreparedStatement statement = statements.get(Query.FIND_BY_ID);
        statement.setLong(1, tuple.first());
        statement.setLong(2, tuple.second());
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            final long firstId = resultSet.getLong(1);
            final long secondId = resultSet.getLong(2);
            final LocalDateTime creationDate = LocalDateTime.parse(resultSet.getString(3));
            final FriendshipDTO friendshipDTO = new FriendshipDTO(creationDate, firstId, secondId);
            return Optional.of(friendshipDTO);
        }
        return Optional.empty();
    }

    @Override
    public void update(FriendshipDTO friendshipDTO) throws SQLException {
    }

    @Override
    public void delete(Tuple<Long, Long> tuple) throws SQLException {
        PreparedStatement statement = statements.get(Query.DELETE);
        statement.setLong(1, tuple.first());
        statement.setLong(2, tuple.second());
        statement.executeUpdate();
    }

    @Override
    public ArrayList<FriendshipDTO> getAll() throws SQLException {
        final ArrayList<FriendshipDTO> friendshipDTOS = new ArrayList<>();
        ResultSet resultSet = statements.get(Query.GET_ALL).executeQuery();
        while (resultSet.next()) {
            final long firstId = resultSet.getLong(1);
            final long secondId = resultSet.getLong(2);
            final LocalDateTime creationDate = LocalDateTime.parse(resultSet.getString(3));
            final FriendshipDTO friendshipDTO = new FriendshipDTO(creationDate, firstId, secondId);
            friendshipDTOS.add(friendshipDTO);
        }
        return friendshipDTOS;
    }
}

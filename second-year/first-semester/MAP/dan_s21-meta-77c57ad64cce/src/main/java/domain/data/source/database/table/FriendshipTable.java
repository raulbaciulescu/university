package domain.data.source.database.table;

import domain.data.source.database.Database;
import domain.data.source.database.Table;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class FriendshipTable implements Table<Long, FriendshipDTO> {

    private final HashMap<Database.Query, PreparedStatement> statements;

    public FriendshipTable(@NotNull final Connection connection) throws SQLException {
        this.statements = new HashMap<>();
        this.initStatements(connection);
    }

    private void initStatements(@NotNull final Connection connection) throws SQLException {
        final PreparedStatement addStatement = connection
                .prepareStatement("insert into meta.public.friendship (creation_date, first_id, second_id) values (?, ?, ?)");
        final PreparedStatement findByIDStatement = connection
                .prepareStatement("select * from meta.public.friendship where (first_id + second_id = ?)");
        final PreparedStatement deleteStatement = connection
                .prepareStatement("delete from meta.public.friendship where (first_id + friendship.second_id = ?)");
        final PreparedStatement getAllStatement = connection
                .prepareStatement("select * from meta.public.friendship");
        this.statements.put(Database.Query.ADD, addStatement);
        this.statements.put(Database.Query.FIND_BY_ID, findByIDStatement);
        this.statements.put(Database.Query.DELETE, deleteStatement);
        this.statements.put(Database.Query.GET_ALL, getAllStatement);
    }


    @Override
    public void insert(@NotNull final FriendshipDTO friendshipDTO) throws SQLException {
        final PreparedStatement addStatement =
                this.statements.get(Database.Query.ADD);
        addStatement.setString(1, friendshipDTO.creationDate().toString());
        addStatement.setLong(2, friendshipDTO.firstID());
        addStatement.setLong(3, friendshipDTO.secondID());
        addStatement.executeUpdate();
    }

    @NotNull
    @Override
    public Optional<FriendshipDTO> whereID(@NotNull final Long id) throws SQLException {
        final PreparedStatement findStatement =
                this.statements.get(Database.Query.FIND_BY_ID);
        findStatement.setLong(1, id);
        final ResultSet resultSet = findStatement.executeQuery();
        return this.fromResultSet(resultSet);
    }

    @NotNull
    @Override
    public Optional<FriendshipDTO> fromResultSet(@NotNull final ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return Optional.empty();
        }
        final LocalDateTime creationDate =
                LocalDateTime.parse(resultSet.getString(1));
        final long firstID = resultSet.getLong(2);
        final long secondID = resultSet.getLong(3);
        final FriendshipDTO dto = new FriendshipDTO(creationDate, firstID, secondID);
        return Optional.of(dto);
    }

    @Override
    public void update(@NotNull final FriendshipDTO object) throws SQLException {}

    @Override
    public void delete(@NotNull final Long id) throws SQLException {
        final PreparedStatement deleteStatement =
                this.statements.get(Database.Query.DELETE);
        deleteStatement.setLong(1, id);
        deleteStatement.executeUpdate();
    }

    @NotNull
    @Override
    public ArrayList<FriendshipDTO> getAll() throws SQLException {
        final ArrayList<FriendshipDTO> friendshipDTOs = new ArrayList<>();
        final PreparedStatement getAllStatement =
                this.statements.get(Database.Query.GET_ALL);
        final ResultSet resultSet = getAllStatement.executeQuery();
        Optional<FriendshipDTO> result = this.fromResultSet(resultSet);
        while (result.isPresent()) {
            friendshipDTOs.add(result.get());
            result = this.fromResultSet(resultSet);
        }
        return friendshipDTOs;
    }
}

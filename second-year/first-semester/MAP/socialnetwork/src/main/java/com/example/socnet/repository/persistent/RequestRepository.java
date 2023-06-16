package com.example.socnet.repository.persistent;

import com.example.socnet.domain.data.MetaDB;
import com.example.socnet.domain.data.table.RequestTable;
import com.example.socnet.domain.model.FriendshipRequest;
import com.example.socnet.repository.memory.Repository;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class RequestRepository extends Repository<Integer, FriendshipRequest> {

    private final RequestTable table;

    public RequestRepository() {
        this.table = (RequestTable) MetaDB.getInstance().table("requests");
    }

    @Override
    public @NotNull Optional<FriendshipRequest> add(@NotNull FriendshipRequest request) throws SQLException {
        final Optional<FriendshipRequest> result = super.add(request);
        if (result.isPresent()) {
            this.table.insert(request);
        }
        return result;
    }

    @Override
    public @NotNull Optional<FriendshipRequest> update(@NotNull FriendshipRequest request) throws SQLException {
        final Optional<FriendshipRequest> result = super.update(request);
        if (result.isPresent()) {
            this.table.update(request);
        }
        return result;
    }

    @Override
    public @NotNull Optional<FriendshipRequest> findByID(@NotNull Integer id) throws SQLException {
        return this.table.whereID(id);
    }

    @Override
    public @NotNull Optional<FriendshipRequest> delete(@NotNull Integer id) throws SQLException {
        final Optional<FriendshipRequest> result = super.delete(id);
        if (result.isPresent()) {
            this.table.delete(id);
        }
        return result;
    }
    @Override
    public @NotNull ArrayList<FriendshipRequest> getAll() throws SQLException {
        return this.table.getAll();
    }
}

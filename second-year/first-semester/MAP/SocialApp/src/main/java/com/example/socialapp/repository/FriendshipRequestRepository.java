package com.example.socialapp.repository;

import com.example.socialapp.database.TableFactory;
import com.example.socialapp.database.table.FriendshipRequestTable;
import com.example.socialapp.domain.FriendshipRequest;
import com.example.socialapp.utils.Constants;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FriendshipRequestRepository implements Repository<Integer, FriendshipRequest> {
    private final FriendshipRequestTable table;

    public FriendshipRequestRepository() throws SQLException {
        this.table = (FriendshipRequestTable) TableFactory.getInstance().table(Constants.Tables.REQUESTS);
    }

    @Override
    public void add(FriendshipRequest request) throws SQLException {
        this.table.insert(request);
    }

    @Override
    public void update(FriendshipRequest request) throws SQLException {
        this.table.update(request);
    }

    @Override
    public Optional<FriendshipRequest> findByID(Integer id) throws SQLException {
        return this.table.findByID(id);
    }

    @Override
    public void delete(Integer id) throws SQLException {
        this.table.delete(id);
    }
    @Override
    public ArrayList<FriendshipRequest> getAll() throws SQLException {
        return this.table.getAll();
    }
}

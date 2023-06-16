package com.example.socialapp.repository;

import com.example.socialapp.database.TableFactory;
import com.example.socialapp.domain.User;

import com.example.socialapp.database.table.UserTable;
import com.example.socialapp.utils.Constants;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class UserRepository implements Repository<Long, User> {
    private final UserTable table;

    public UserRepository() throws SQLException {
        this.table = (UserTable) TableFactory.getInstance().table(Constants.Tables.USERS);
    }

    @Override
    public void add(User user) throws SQLException {
        table.insert(user);
    }

    @Override
    public void update(User user) throws SQLException {
        table.update(user);
    }

    @Override
    public Optional<User> findByID(Long id) throws SQLException {
        return table.findByID(id);
    }

    @Override
    public void delete(Long id) throws SQLException {
        table.delete(id);
    }

    @Override
    public ArrayList<User> getAll() throws SQLException {
        return table.getAll();
    }
}

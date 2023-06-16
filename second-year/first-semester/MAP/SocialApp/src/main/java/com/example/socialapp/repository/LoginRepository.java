package com.example.socialapp.repository;

import com.example.socialapp.database.TableFactory;
import com.example.socialapp.database.table.LoginTable;

import com.example.socialapp.domain.UserLogin;
import com.example.socialapp.utils.Constants;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class LoginRepository implements Repository<Long, UserLogin> {
    private LoginTable table;

    public LoginRepository() throws SQLException {
        this.table = (LoginTable) TableFactory.getInstance().table(Constants.Tables.LOGIN);
    }

    @Override
    public void add(UserLogin user) throws SQLException {
        table.insert(user);
    }

    @Override
    public void update(UserLogin user) throws SQLException {
        table.update(user);
    }

    @Override
    public Optional<UserLogin> findByID(Long id) throws SQLException {
        return table.findByID(id);
    }

    @Override
    public void delete(Long id) throws SQLException {
        table.delete(id);
    }

    @Override
    public ArrayList<UserLogin> getAll() throws SQLException {
        return table.getAll();
    }

    public Optional<UserLogin> find(String username, String password) throws SQLException {
        return table.find(username, password);
    }
}

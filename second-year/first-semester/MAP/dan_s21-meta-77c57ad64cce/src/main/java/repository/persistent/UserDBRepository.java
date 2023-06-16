package repository.persistent;

import domain.data.source.database.MetaDB;
import domain.data.source.database.table.UserTable;
import domain.model.User;
import org.jetbrains.annotations.NotNull;
import repository.memory.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class UserDBRepository extends Repository<Long, User> {

    private final UserTable table;

    public UserDBRepository() throws SQLException {
        this.table = (UserTable) MetaDB.getInstance().table("users");
        for (@NotNull final User user : this.table.getAll()) {
            super.add(user);
        }
    }


    @Override
    public @NotNull Optional<User> add(@NotNull final User user) throws SQLException {
        final Optional<User> result = super.add(user);
        if (result.isPresent()) {
            this.table.insert(user);
        }
        return result;
    }

    @Override
    public @NotNull Optional<User> update(@NotNull final User user) throws SQLException {
        final Optional<User> result = super.update(user);
        if (result.isPresent()) {
            this.table.update(user);
        }
        return result;
    }

    @Override
    public @NotNull Optional<User> delete(@NotNull final Long id) throws SQLException {
        final Optional<User> result = super.delete(id);
        if (result.isPresent()) {
            this.table.delete(id);
        }
        return result;
    }

    @Override
    public @NotNull ArrayList<User> getAll() throws SQLException {
        return this.table.getAll();
    }
}

package impl.database;

import api.Constants;
import api.Table;
import domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserTable implements Table<Long, User> {
    private final Connection connection;
    private final Map<Constants.Db.Queries, PreparedStatement> statements;
    private static final Logger logger = LogManager.getLogger();

    public UserTable(Connection connection) throws SQLException {
        logger.info("Initializing UserTable");
        this.statements = new HashMap<>();
        this.connection = connection;
        initStatements();
    }

    void initStatements() throws SQLException {
        statements.put(Constants.Db.Queries.ADD,
                connection.prepareStatement("INSERT INTO user (id, username, password, firstName, lastName) VALUES (?, ?, ?, ?, ?)"
                ));
        statements.put(Constants.Db.Queries.DELETE,
                connection.prepareStatement("DELETE FROM user WHERE id = ?"
                ));
        statements.put(Constants.Db.Queries.FIND_BY_ID,
                connection.prepareStatement("SELECT * FROM user WHERE id = ?"
                ));
        statements.put(Constants.Db.Queries.GET_ALL,
                connection.prepareStatement("SELECT * FROM user"
                ));
        statements.put(Constants.Db.Queries.FIND2,
                connection.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?"
                ));

    }
    @Override
    public void add(User user) {
        logger.traceEntry("saving user {} ", user);
        PreparedStatement statement = statements.get(Constants.Db.Queries.ADD);
        try {
            statement.setLong(1, user.getId());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());
            int result = statement.executeUpdate();
            logger.trace("Saved {} instances", result);
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB " + e);
        }
        logger.traceExit();
    }

    @Override
    public void delete(Long o) {

    }

    @Override
    public void update(User elem, User newElem) {

    }

    @Override
    public Optional<User> findById(Long id) {
        logger.traceEntry();
        List<User> users = new ArrayList<>();
        PreparedStatement statement = statements.get(Constants.Db.Queries.FIND_BY_ID);
        try {
            statement.setLong(1, id);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    long id1 = result.getLong("id");
                    String username = result.getString("username");
                    String password = result.getString("password");
                    String firstName = result.getString("firstName");
                    String lastName = result.getString("lastName");
                    User user = new User(username, password, firstName, lastName);
                    user.setId(id1);
                    users.add(user);
                }
            }
        } catch (SQLException throwables) {
            logger.error(throwables);
            System.out.println("Error db" + throwables);
        }
        logger.traceExit();
        return Optional.of(users.get(0));
    }

    @Override
    public List<User> getAll() {
        logger.traceEntry();
        List<User> users = new ArrayList<>();
        PreparedStatement statement = statements.get(Constants.Db.Queries.GET_ALL);
        try {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    long id1 = result.getLong("id");
                    String username = result.getString("username");
                    String password = result.getString("password");
                    String firstName = result.getString("firstName");
                    String lastName = result.getString("lastName");
                    User user = new User(username, password, firstName, lastName);
                    user.setId(id1);
                    users.add(user);
                }
            }
        } catch (SQLException throwables) {
            logger.error(throwables);
            System.out.println("Error db" + throwables);
        }
        logger.traceExit();
        return users;
    }

    public Optional<User> findUser(String username, String password) {
        logger.traceEntry();
        List<User> users = new ArrayList<>();
        PreparedStatement statement = statements.get(Constants.Db.Queries.FIND2);
        try {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    long id1 = result.getLong("id");
                    String username1 = result.getString("username");
                    String password1 = result.getString("password");
                    String firstName = result.getString("firstName");
                    String lastName = result.getString("lastName");
                    User user = new User(username, password, firstName, lastName);
                    user.setId(id1);
                    users.add(user);
                }
            }
        } catch (SQLException throwables) {
            logger.error(throwables);
            System.out.println("Error db" + throwables);
        }
        logger.traceExit();
        return Optional.of(users.get(0));
    }
}

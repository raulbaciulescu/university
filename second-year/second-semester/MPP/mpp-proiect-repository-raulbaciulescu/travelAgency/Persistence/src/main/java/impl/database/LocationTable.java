package impl.database;

import api.Constants;
import api.Table;
import domain.Location;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class LocationTable implements Table<Long, Location> {
    private final Connection connection;
    private Map<Constants.Db.Queries, PreparedStatement> statements;
    private static final Logger logger = LogManager.getLogger();

    public LocationTable(Connection connection) throws SQLException {
        logger.info("Initializing LocationTable");
        this.statements = new HashMap<>();
        this.connection = connection;
        initStatements();
    }

    void initStatements() throws SQLException {
        statements.put(Constants.Db.Queries.ADD,
                connection.prepareStatement("INSERT INTO location (id, name, airport) VALUES (?, ?, ?)"
                ));
        statements.put(Constants.Db.Queries.DELETE,
                connection.prepareStatement("DELETE FROM location WHERE id = ?"
                ));
        statements.put(Constants.Db.Queries.FIND_BY_ID,
                connection.prepareStatement("SELECT * FROM location WHERE id = ?"
                ));
        statements.put(Constants.Db.Queries.GET_ALL,
                connection.prepareStatement("SELECT * FROM location"
                ));
    }
    @Override
    public void add(Location location) {
        logger.traceEntry("saving location {} ", location);
        PreparedStatement statement = statements.get(Constants.Db.Queries.ADD);
        try {
            statement.setLong(1, location.getId());
            statement.setString(2, location.getName());
            statement.setString(3, location.getAirport());
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
    public void update(Location elem, Location newElem) {

    }

    @Override
    public Optional<Location> findById(Long id) {
        logger.traceEntry();
        List<Location> locations = new ArrayList<>();
        PreparedStatement statement = statements.get(Constants.Db.Queries.FIND_BY_ID);
        try {
            statement.setLong(1, id);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    long id1 = result.getLong("id");
                    String name = result.getString("name");
                    String airport = result.getString("airport");
                    Location location = new Location(name, airport);
                    location.setId(id1);
                    locations.add(location);
                }
            }
        } catch (SQLException throwables) {
            logger.error(throwables);
            System.out.println("Error db" + throwables);
        }
        logger.traceExit();
        return Optional.of(locations.get(0));
    }

    @Override
    public List<Location> getAll() {
        logger.traceEntry();
        List<Location> locations = new ArrayList<>();
        PreparedStatement statement = statements.get(Constants.Db.Queries.GET_ALL);
        try {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    long id1 = result.getLong("id");
                    String name = result.getString("name");
                    String airport = result.getString("airport");
                    Location location = new Location(name, airport);
                    location.setId(id1);
                    locations.add(location);
                }
            }
        } catch (SQLException throwables) {
            logger.error(throwables);
            System.out.println("Error db" + throwables);
        }
        logger.traceExit();
        return locations;
    }
}

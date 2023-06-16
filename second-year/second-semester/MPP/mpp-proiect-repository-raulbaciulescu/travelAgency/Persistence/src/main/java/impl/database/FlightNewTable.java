package impl.database;

import api.Constants;
import api.Table;
import domain.FlightNew;
import domain.dto.FlightDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

public class FlightNewTable implements Table<Integer, FlightNew> {
    private final Connection connection;
    private final Map<Constants.Db.Queries, PreparedStatement> statements;
    private static final Logger logger = LogManager.getLogger();

    public FlightNewTable(Connection connection) throws SQLException {
        logger.info("Initializing FlightTable");
        this.statements = new HashMap<>();
        this.connection = connection;
        initStatements();
    }

    void initStatements() throws SQLException {
        statements.put(Constants.Db.Queries.ADD,
                connection.prepareStatement("INSERT INTO flight_new (start, destination, startDate, nrOfSeats) VALUES (?, ?, ?, ?)"
                ));
        statements.put(Constants.Db.Queries.DELETE,
                connection.prepareStatement("DELETE FROM flight_new WHERE id = ?"
                ));
        statements.put(Constants.Db.Queries.FIND_BY_ID,
                connection.prepareStatement("SELECT * FROM flight_new WHERE id = ?"
                ));
        statements.put(Constants.Db.Queries.GET_ALL,
                connection.prepareStatement("SELECT * FROM flight_new"
                ));
        statements.put(Constants.Db.Queries.UPDATE,
                connection.prepareStatement("UPDATE flight_new SET nrOfSeats = ?, start = ?, destination = ?, startDate = ? WHERE id = ?"));
    }

    @Override
    public void add(FlightNew flightNew) {
        logger.traceEntry("saving flight {} ", flightNew);
        PreparedStatement statement = statements.get(Constants.Db.Queries.ADD);
        try {
            statement.setString(1, flightNew.getStart());
            statement.setString(2, flightNew.getDestination());
            statement.setDate(3, new java.sql.Date(flightNew.getStartDate().getTime()));
            statement.setInt(4, flightNew.getNrOfSeats());
            int result = statement.executeUpdate();
            logger.trace("Saved {} instances", result);
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB " + e);
        }
        logger.traceExit();
    }

    @Override
    public void delete(Integer id) {
        logger.traceEntry("delete flight with id {} ", id);
        PreparedStatement statement = statements.get(Constants.Db.Queries.DELETE);
        try {
            statement.setLong(1, id);
            int result = statement.executeUpdate();
            logger.trace("Deleted {} instances", result);
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB " + e);
        }
        logger.traceExit();
    }

    @Override
    public void update(FlightNew elem, FlightNew newElem) {
        logger.traceEntry();
        PreparedStatement statement = statements.get(Constants.Db.Queries.UPDATE);
        try {
            statement.setInt(1, newElem.getNrOfSeats());
            statement.setString(2, newElem.getStart());
            statement.setString(3, newElem.getDestination());
            statement.setDate(4, new java.sql.Date(newElem.getStartDate().getTime()));
            statement.setLong(5, elem.getId());
            int result = statement.executeUpdate();
            logger.trace("Updated {} instances", result);
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB " + e);
        }
        logger.traceExit();
    }

    @Override
    public Optional<FlightNew> findById(Integer id) {
        logger.traceEntry();
        List<FlightNew> flights = new ArrayList<>();
        PreparedStatement statement = statements.get(Constants.Db.Queries.FIND_BY_ID);
        try {
            statement.setLong(1, id);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    int id1 = result.getInt("id");
                    String start = result.getString("start");
                    String destination = result.getString("destination");
                    Date startDate = result.getDate("startDate");
                    int nrOfSeats = result.getInt("nrOfSeats");
                    FlightNew flight = new FlightNew(start, destination, startDate, nrOfSeats);
                    flight.setId(id1);
                    flights.add(flight);
                }
            }
        } catch (SQLException throwables) {
            logger.error(throwables);
            System.out.println("Error db" + throwables);
        }
        logger.traceExit();
        if (flights.size() > 0)
            return Optional.of(flights.get(0));
        else
            return Optional.empty();
    }

    @Override
    public List<FlightNew> getAll() {
        logger.traceEntry();
        List<FlightNew> flights = new ArrayList<>();
        PreparedStatement statement = statements.get(Constants.Db.Queries.GET_ALL);
        try {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    int id1 = result.getInt("id");
                    String start = result.getString("start");
                    String destination = result.getString("destination");
                    Date startDate = result.getDate("startDate");
                    int nrOfSeats = result.getInt("nrOfSeats");
                    FlightNew flight = new FlightNew(start, destination, startDate, nrOfSeats);
                    flight.setId(id1);
                    flights.add(flight);
                }
            }
        } catch (SQLException throwables) {
            logger.error(throwables);
            System.out.println("Error db" + throwables);
        }
        logger.traceExit();
        return flights;
    }
}

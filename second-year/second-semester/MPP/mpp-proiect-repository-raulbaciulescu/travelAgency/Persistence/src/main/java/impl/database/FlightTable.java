package impl.database;

import api.Constants;
import api.Table;
import domain.dto.FlightDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

public class FlightTable implements Table<Long, FlightDto> {
    private final Connection connection;
    private final Map<Constants.Db.Queries, PreparedStatement> statements;
    private static final Logger logger = LogManager.getLogger();

    public FlightTable(Connection connection) throws SQLException {
        logger.info("Initializing FlightTable");
        this.statements = new HashMap<>();
        this.connection = connection;
        initStatements();
    }

    void initStatements() throws SQLException {
        statements.put(Constants.Db.Queries.ADD,
                connection.prepareStatement("INSERT INTO flight (id, startId, destinationId, startDate, nrOfSeats) VALUES (?, ?, ?, ?, ?)"
                ));
        statements.put(Constants.Db.Queries.DELETE,
                connection.prepareStatement("DELETE FROM flight WHERE id = ?"
                ));
        statements.put(Constants.Db.Queries.FIND_BY_ID,
                connection.prepareStatement("SELECT * FROM flight WHERE id = ?"
                ));
        statements.put(Constants.Db.Queries.GET_ALL,
                connection.prepareStatement("SELECT * FROM flight"
                ));
        statements.put(Constants.Db.Queries.UPDATE,
                connection.prepareStatement("UPDATE flight SET nrOfSeats = ?, startId = ?, destinationId = ?, startDate = ? WHERE id = ?"));
    }

    @Override
    public void add(FlightDto flightDto) {
        logger.traceEntry("saving flight {} ", flightDto);
        PreparedStatement statement = statements.get(Constants.Db.Queries.ADD);
        try {
            statement.setLong(1, flightDto.getId());
            statement.setLong(2, flightDto.getStartId());
            statement.setLong(3, flightDto.getDestinationId());
            statement.setString(4, flightDto.getStartDate().toString());
            statement.setInt(5, flightDto.getNrOfSeats());
            int result = statement.executeUpdate();
            logger.trace("Saved {} instances", result);
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB " + e);
        }
        logger.traceExit();
    }

    @Override
    public void delete(Long id) {
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
    public void update(FlightDto elem, FlightDto newElem) {
        logger.traceEntry();
        PreparedStatement statement = statements.get(Constants.Db.Queries.UPDATE);
        try {
            statement.setInt(1, newElem.getNrOfSeats());
            statement.setLong(2, newElem.getStartId());
            statement.setLong(3, newElem.getDestinationId());
            statement.setString(4, newElem.getStartDate().toString());
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
    public Optional<FlightDto> findById(Long id) {
        logger.traceEntry();
        List<FlightDto> flights = new ArrayList<>();
        PreparedStatement statement = statements.get(Constants.Db.Queries.FIND_BY_ID);
        try {
            statement.setLong(1, id);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    long id1 = result.getLong("id");
                    long startId = result.getLong("startId");
                    long destinationId = result.getLong("destinationId");
                    LocalDateTime startDate = LocalDateTime.parse(result.getString("startDate"));
                    int nrOfSeats = result.getInt("nrOfSeats");
                    FlightDto flightDto = new FlightDto(startId, destinationId, startDate, nrOfSeats);
                    flightDto.setId(id1);
                    flights.add(flightDto);
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
    public List<FlightDto> getAll() {
        logger.traceEntry();
        List<FlightDto> flights = new ArrayList<>();
        PreparedStatement statement = statements.get(Constants.Db.Queries.GET_ALL);
        try {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    long id1 = result.getLong("id");
                    long startId = result.getLong("startId");
                    long destinationId = result.getLong("destinationId");
                    LocalDateTime startDate = LocalDateTime.parse(result.getString("startDate"));
                    int nrOfSeats = result.getInt("nrOfSeats");
                    FlightDto flightDto = new FlightDto(startId, destinationId, startDate, nrOfSeats);
                    flightDto.setId(id1);
                    flights.add(flightDto);
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

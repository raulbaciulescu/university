package impl.database;

import api.Constants;
import api.Table;
import domain.dto.PurchaseDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PurchaseTable implements Table<Long, PurchaseDto> {

    private final Connection connection;
    private Map<Constants.Db.Queries, PreparedStatement> statements;
    private Map<Constants.Db.Queries, PreparedStatement> statementsTourist;
    private static final Logger logger = LogManager.getLogger();

    public PurchaseTable(Connection connection) throws SQLException {
        logger.info("Initializing PurchaseTable");
        this.statements = new HashMap<>();
        this.statementsTourist = new HashMap<>();
        this.connection = connection;
        initStatements();
    }

    void initStatements() throws SQLException {
        statements.put(Constants.Db.Queries.ADD,
                connection.prepareStatement("INSERT INTO purchase (id, flightId, clientName, clientAddress, nrOfSeats) VALUES (?, ?, ?, ?, ?)"
                ));
        statements.put(Constants.Db.Queries.DELETE,
                connection.prepareStatement("DELETE FROM purchase WHERE id = ?"
                ));
        statements.put(Constants.Db.Queries.FIND_BY_ID,
                connection.prepareStatement("SELECT * FROM purchase WHERE id = ?"
                ));
        statements.put(Constants.Db.Queries.GET_ALL,
                connection.prepareStatement("SELECT * FROM purchase"
                ));

        statementsTourist.put(Constants.Db.Queries.ADD,
                connection.prepareStatement("INSERT INTO tourist (name, purchaseId) VALUES (?, ?)"
                ));
        statementsTourist.put(Constants.Db.Queries.FIND_BY_ID,
                connection.prepareStatement("SELECT * FROM tourist WHERE purchaseId = ?"
                ));

    }
    @Override
    public void add(PurchaseDto purchaseDto) {
        logger.traceEntry("saving purchase {} ", purchaseDto);
        PreparedStatement statement = statements.get(Constants.Db.Queries.ADD);
        try {
            statement.setLong(1, purchaseDto.getId());
            statement.setLong(2, purchaseDto.getFlightId());
            statement.setString(3, purchaseDto.getClientName());
            statement.setString(4, purchaseDto.getClientAddress());
            statement.setInt(5, purchaseDto.getNrOfSeats());
            addTourists(purchaseDto.getId(), purchaseDto.getTourists());
            int result = statement.executeUpdate();
            logger.trace("Saved {} instances", result);
        } catch (SQLException e) {
            logger.error(e);
            System.err.println("Error DB " + e);
        }
        logger.traceExit();
    }

    private void addTourists(Long id, List<String> tourists) {
        logger.traceEntry("saving tourist {} ", tourists);
        for (String tourist: tourists) {
            PreparedStatement statement = statementsTourist.get(Constants.Db.Queries.ADD);
            try {
                statement.setString(1, tourist);
                statement.setLong(2, id);
                int result = statement.executeUpdate();
                logger.trace("Saved {} instances", result);
            } catch (SQLException e) {
                logger.error(e);
                System.err.println("Error DB " + e);
            }
        }
        logger.traceExit();
    }

    @Override
    public void delete(Long o) {
        //TODO
    }

    @Override
    public void update(PurchaseDto elem, PurchaseDto newElem) {

    }

    @Override
    public Optional<PurchaseDto> findById(Long id) {
        logger.traceEntry();
        List<PurchaseDto> purchaseDtos = new ArrayList<>();
        PreparedStatement statement = statements.get(Constants.Db.Queries.FIND_BY_ID);
        try {
            statement.setLong(1, id);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    long id1 = result.getLong("id");
                    long flightId = result.getLong("flightId");
                    String clientName = result.getString("clientName");
                    String clientAddress = result.getString("clientAddress");
                    int nrOfSeats = result.getInt("nrOfSeats");
                    List<String> tourists = getTourists(id);

                    PurchaseDto purchaseDto = new PurchaseDto(flightId, clientName, clientAddress, tourists, nrOfSeats);
                    purchaseDto.setId(id1);
                    purchaseDtos.add(purchaseDto);
                }
            }
        } catch (SQLException throwables) {
            logger.error(throwables);
            System.out.println("Error db" + throwables);
        }
        logger.traceExit();
        return Optional.of(purchaseDtos.get(0));
    }

    private List<String> getTourists(Long id) {
        logger.traceEntry();
        List<String> tourists = new ArrayList<>();
        PreparedStatement statement = statementsTourist.get(Constants.Db.Queries.FIND_BY_ID);
        try {
            statement.setLong(1, id);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    String name = result.getString("name");
                    tourists.add(name);
                }
            }
        } catch (SQLException throwables) {
            logger.error(throwables);
            System.out.println("Error db" + throwables);
        }
        logger.traceExit();
        return tourists;
    }

    @Override
    public List<PurchaseDto> getAll() {
        logger.traceEntry();
        List<PurchaseDto> purchaseDtos = new ArrayList<>();
        PreparedStatement statement = statements.get(Constants.Db.Queries.GET_ALL);
        try {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    long id1 = result.getLong("id");
                    long flightId = result.getLong("flightId");
                    String clientName = result.getString("clientName");
                    String clientAddress = result.getString("clientAddress");
                    int nrOfSeats = result.getInt("nrOfSeats");
                    List<String> tourists = getTourists(id1);

                    PurchaseDto purchaseDto = new PurchaseDto(flightId, clientName, clientAddress, tourists, nrOfSeats);
                    purchaseDto.setId(id1);
                    purchaseDtos.add(purchaseDto);
                }
            }
        } catch (SQLException throwables) {
            logger.error(throwables);
            System.out.println("Error db" + throwables);
        }
        logger.traceExit();
        return purchaseDtos;
    }
}

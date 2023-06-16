package exam.persistence.db;

import exam.model.Player;
import exam.persistence.IPlayerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

@Component
public class PlayerDbRepository implements IPlayerRepository {
    private JdbcUtils dbUtils;

    private static final Logger logger = LogManager.getLogger();

    public PlayerDbRepository(Properties properties) {
        logger.info("Initializing PlayerRepo with properties: {} ", properties);
        dbUtils = new JdbcUtils(properties);
    }


    @Override
    public Player add(Player elem) {
        return null;
    }

    @Override
    public void delete(String s) {

    }

    @Override
    public void update(Player elem) {

    }

    @Override
    public Player findById(String s) {
        logger.traceEntry();
        Connection connection = dbUtils.getConnection();
        Player player = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from players where alias=?")) {
            preparedStatement.setString(1, s);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String alias=resultSet.getString("alias");
                    player = new Player(alias);

                }
            }
        } catch (SQLException ex) {
            logger.error(ex);
        }
        logger.traceExit();
        return player;
    }

    @Override
    public List<Player> findAll() {
        return null;
    }
}

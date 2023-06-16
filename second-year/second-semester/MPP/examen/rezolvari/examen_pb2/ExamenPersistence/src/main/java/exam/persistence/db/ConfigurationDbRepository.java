package exam.persistence.db;

import exam.model.Configuration;
import exam.persistence.IConfigurationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

@Component
public class ConfigurationDbRepository implements IConfigurationRepository {
    private JdbcUtils dbUtils;

    private static final Logger logger = LogManager.getLogger();

    public ConfigurationDbRepository(Properties properties) {
        logger.info("Initializing ConfRepo with properties: {} ", properties);
        dbUtils = new JdbcUtils(properties);
    }

    @Override
    public Configuration add(Configuration elem) {
        logger.traceEntry("saving game {} ", elem);
        Connection connection = dbUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into configurations (x,y,value) values (?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, elem.getX());
            preparedStatement.setInt(2, elem.getY());
            preparedStatement.setInt(3,elem.getValue());

            int result = preparedStatement.executeUpdate();
            logger.trace("Saved {} instances", result);
            ResultSet rs= preparedStatement.getGeneratedKeys();
            int id;
            if(rs.next()){
                id=rs.getInt(1);
                elem.setID(id);
            }

        } catch (SQLException ex) {
            logger.error(ex);
        }
        logger.traceExit();
        return elem;
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public void update(Configuration elem) {

    }

    @Override
    public Configuration findById(Integer integer) {
        return null;
    }

    @Override
    public List<Configuration> findAll() {
        return null;
    }

    @Override
    public Configuration getRandomConfig() {
        logger.traceEntry();
        Connection connection = dbUtils.getConnection();
        List<Configuration> configs= new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from configurations ")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id=resultSet.getInt("id");
                    int x=resultSet.getInt("x");
                    int y=resultSet.getInt("y");
                    int value=resultSet.getInt("value");
                    Configuration conf=new Configuration(id,x,y,value);
                    configs.add(conf);

                }
            }
        } catch (SQLException ex) {
            logger.error(ex);
        }
        Random random=new Random();
        int poz=random.nextInt(configs.size());
        logger.traceExit();
        return configs.get(poz);
    }
}

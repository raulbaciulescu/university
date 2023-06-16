package exam.persistence.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    private Properties jdbcProperties;

    private static final Logger logger= LogManager.getLogger();

    private static Connection connection=null;

    public JdbcUtils(Properties properties){
        jdbcProperties=properties;
    }
    private Connection getNewConnection(){
        logger.traceEntry();

        String driver=jdbcProperties.getProperty("exam.jdbc.driver");
        String url=jdbcProperties.getProperty("exam.jdbc.url");
        String user=jdbcProperties.getProperty("exam.jdbc.user");
        String password= jdbcProperties.getProperty("exam.jdbc.password");
        logger.info("trying to connect to database ... {}",url);
        logger.info("user: {}",user);
        logger.info("pass: {}", password);
        Connection newConnection=null;
        try {
            Class.forName(driver);
            if (user != null && password != null)
                newConnection = DriverManager.getConnection(url, user, password);
            else
                newConnection = DriverManager.getConnection(url);
        }catch(SQLException | ClassNotFoundException exception){
            logger.error(exception);
            System.out.println("Error getting connection "+exception);
        }
        return newConnection;
    }
    public Connection getConnection(){
        logger.traceEntry();
        try {
            if(connection==null|| connection.isClosed())
                connection=getNewConnection();
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB "+e);
        }
        logger.traceExit(connection);
        return connection;
    }
    public Properties getJdbcProperties(){
        return jdbcProperties;
    }
}

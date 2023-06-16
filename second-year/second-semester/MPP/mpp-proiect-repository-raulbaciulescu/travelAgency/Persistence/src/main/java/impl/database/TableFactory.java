package impl.database;

import api.Constants;
import api.Table;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

@Component
public class TableFactory {
    private final JdbcUtils dbUtils;

    public TableFactory() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(Constants.Db.filename));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config " + e);
        }
        dbUtils = new JdbcUtils(properties);
    }

    public Table<?, ?> getTable(Constants.Db.Tables table) throws SQLException {
        if (table == Constants.Db.Tables.USER)
            return new UserTable(dbUtils.getConnection());
        if (table == Constants.Db.Tables.FLIGHT)
            return new FlightTable(dbUtils.getConnection());
        if (table == Constants.Db.Tables.PURCHASE)
            return new PurchaseTable(dbUtils.getConnection());
        if (table == Constants.Db.Tables.FLIGHT_NEW)
            return new FlightNewTable(dbUtils.getConnection());

        return new LocationTable(dbUtils.getConnection());
    }
}

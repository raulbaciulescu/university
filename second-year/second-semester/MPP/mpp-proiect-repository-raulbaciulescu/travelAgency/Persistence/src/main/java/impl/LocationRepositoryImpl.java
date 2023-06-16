package impl;

import api.Constants;
import api.LocationRepository;
import api.Table;
import domain.Location;
import impl.database.LocationTable;
import impl.database.TableFactory;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class LocationRepositoryImpl implements LocationRepository {
    private final Table<Long, Location> table;

    public LocationRepositoryImpl(TableFactory factory) throws SQLException {
        table = (LocationTable) factory.getTable(Constants.Db.Tables.LOCATION);
    }


    @Override
    public void add(Location entity) {
        table.add(entity);
    }

    @Override
    public void update(Location entity, Location newEntity) {

    }

    @Override
    public Optional<Location> findByID(Long aLong) {
        return table.findById(aLong);
    }

    @Override
    public void delete(Long aLong) {
        //TODO
    }

    @Override
    public List<Location> getAll() {
        return table.getAll();
    }
}

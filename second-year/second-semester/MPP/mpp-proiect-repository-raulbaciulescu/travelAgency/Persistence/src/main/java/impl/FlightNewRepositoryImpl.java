package impl;

import api.Constants;
import api.FlightNewRepository;
import api.LocationRepository;
import api.Table;
import domain.Flight;
import domain.FlightNew;
import domain.Location;
import domain.User;
import domain.dto.FlightDto;
import impl.database.FlightNewTable;
import impl.database.FlightTable;
import impl.database.TableFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class FlightNewRepositoryImpl implements FlightNewRepository {
    private final Table<Integer, FlightNew> table;

    public FlightNewRepositoryImpl(TableFactory factory) throws SQLException {
        table = (FlightNewTable) factory.getTable(Constants.Db.Tables.FLIGHT_NEW);
    }

    @Override
    public void add(FlightNew flight) {
        table.add(flight);
    }

    @Override
    public void update(FlightNew entity, FlightNew newEntity) {
        table.update(entity, newEntity);
    }

    @Override
    public Optional<FlightNew> findByID(Integer id) {
        return table.findById(id);
    }

    @Override
    public void delete(Integer aLong) {
        table.delete(aLong);
    }

    @Override
    public List<FlightNew> getAll() {
        return table.getAll();
    }
//    private static SessionFactory sessionFactory;
//
//    public FlightNewRepositoryImpl() {
//        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//                .configure() // configures settings from hibernate.cfg.xml
//                .build();
//        try {
//            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
//        }
//        catch (Exception e) {
//            System.err.println("Exceptie "+e);
//            StandardServiceRegistryBuilder.destroy(registry);
//        }
//    }
//
//    static void close() {
//        if (sessionFactory != null) {
//            sessionFactory.close();
//        }
//    }
//
//    @Override
//    public void add(FlightNew entity) {
//        try (Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//            session.save(new FlightNew(entity.getId(),
//                    entity.getStart(),
//                    entity.getDestination(),
//                    entity.getStartDate(),
//                    entity.getNrOfSeats()
//            ));
//            session.getTransaction().commit();
//        }
//    }
//
//    @Override
//    public void update(FlightNew entity, FlightNew newEntity) {
//        try (Session session = sessionFactory.openSession()){
//            Transaction tx = null;
//            try{
//                tx = session.beginTransaction();
//                FlightNew flight = session.load(FlightNew.class, entity.getId());
//                flight.setStart(newEntity.getStart());
//                flight.setDestination(newEntity.getDestination());
//                flight.setStartDate(newEntity.getStartDate());
//                flight.setNrOfSeats(newEntity.getNrOfSeats());
//                tx.commit();
//
//            } catch (RuntimeException ex) {
//                System.err.println("Eroare la update " + ex);
//                if (tx != null)
//                    tx.rollback();
//            }
//        }
//    }
//
//    @Override
//    public void delete(Integer id) {
//        try (Session session = sessionFactory.openSession()) {
//            Transaction tx = null;
//            try {
//                tx = session.beginTransaction();
//                FlightNew flightNew = session.createQuery("from FlightNew where id = :id", FlightNew.class)
//                        .setParameter("id", id).setMaxResults(1).uniqueResult();
//                session.delete(flightNew);
//                tx.commit();
//            } catch (RuntimeException ex) {
//                System.err.println("Eroare la stergere " + ex);
//                if (tx != null)
//                    tx.rollback();
//            }
//        }
//    }
//
//    @Override
//    public Optional<FlightNew> findByID(Integer id) {
//        try (Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//            String sql = "from FlightNew where id = :id";
//            List<FlightNew> result = session.createQuery(sql, FlightNew.class)
//                    .setParameter("id", id).list();
//            for (FlightNew flight :  result) {
//                return Optional.of(flight);
//            }
//            session.getTransaction().commit();
//        }
//        return Optional.empty();
//    }
//
//    @Override
//    public List<FlightNew> getAll() {
//        List<FlightNew> result = new ArrayList<>();
//
//        try (Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//            result = session.createQuery("FROM FlightNew", FlightNew.class).list();
//            session.getTransaction().commit();
//        }
//        catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return result;
//    }
}

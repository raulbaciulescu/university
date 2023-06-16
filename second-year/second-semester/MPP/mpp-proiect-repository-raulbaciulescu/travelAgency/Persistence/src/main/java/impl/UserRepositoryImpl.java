package impl;

import api.Constants;
import api.Table;
import api.UserRepository;
import domain.User;
import impl.database.TableFactory;
import impl.database.UserTable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private final UserTable table;
    private static SessionFactory sessionFactory;


    public UserRepositoryImpl(TableFactory factory) throws SQLException {
        table = (UserTable) factory.getTable(Constants.Db.Tables.USER);

        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            System.err.println("Exceptie "+e);
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    static void close() {
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }

    @Override
    public void add(User entity) {
        //table.add(entity);
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(new User(entity.getId(), entity.getUsername(), entity.getPassword()));
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(User entity, User newEntity) {
    }


    @Override
    public Optional<User> findByID(Long id) {
        return table.findById(id);
    }

    @Override
    public void delete(Long id) {
        table.delete(id);
    }

    @Override
    public List<User> getAll() {
        //return table.getAll();
        List<User> result;
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            result = session.createQuery("from User", User.class).list();
            session.getTransaction().commit();
        }
        return result;
    }

    @Override
    public Optional<User> findUser(String username, String password) {
        //return table.findUser(username, password);
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            String sql = "from User where username = :username and password = :password";
            List<User> result = session.createQuery(sql, User.class)
                    .setParameter("username", username).setParameter("password", password).list();
            for (User user :  result) {
                return Optional.of(user);
            }
            session.getTransaction().commit();
        }
        return Optional.empty();
    }
}

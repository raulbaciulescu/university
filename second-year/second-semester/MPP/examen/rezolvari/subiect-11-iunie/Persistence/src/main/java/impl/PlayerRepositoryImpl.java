package impl;

import api.PlayerRepository;
import domain.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;
import java.util.Optional;

public class PlayerRepositoryImpl implements PlayerRepository {
    private static SessionFactory sessionFactory;

    public PlayerRepositoryImpl() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            System.err.println("Exception " + e);
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    static void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Override
    public void add(Player entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Exception " + ex);
        }
    }

    @Override
    public void update(Player entity, Player newEntity) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public Optional<Player> findByID(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<Player> getAll() {
        return null;
    }

    @Override
    public Optional<Player> findPlayer(String username, String password) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            String sql = "from Player where username = :username and password = :password";
            List<Player> result = session.createQuery(sql, Player.class)
                    .setParameter("username", username).setParameter("password", password).list();
            for (Player player :  result) {
                return Optional.of(player);
            }
            session.getTransaction().commit();
        }
        return Optional.empty();
    }
}

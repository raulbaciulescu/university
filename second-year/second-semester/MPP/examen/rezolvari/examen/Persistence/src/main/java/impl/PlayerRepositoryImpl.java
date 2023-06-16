package impl;

import api.PlayerRepository;
import domain.Game;
import domain.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
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
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            String sql = "from Player where id = :id";
            List<Player> result = session.createQuery(sql, Player.class)
                    .setParameter("id", integer).list();
            for (Player player :  result) {
                return Optional.of(player);
            }
            session.getTransaction().commit();
        }
        return Optional.empty();
    }

    @Override
    public List<Player> getAll() {
        List<Player> result = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            result = session.createQuery("from Player", Player.class).list();
            session.getTransaction().commit();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Optional<Player> findPlayer(String alias) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            String sql = "from Player where alias = :al";
            List<Player> result = session.createQuery(sql, Player.class)
                    .setParameter("al", alias).list();
            for (Player player :  result) {
                return Optional.of(player);
            }
            session.getTransaction().commit();
        }
        return Optional.empty();
    }
}

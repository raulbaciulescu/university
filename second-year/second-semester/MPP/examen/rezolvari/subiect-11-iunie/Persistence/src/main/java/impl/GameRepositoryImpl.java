//package impl;
//
//import api.GameRepository;
//import domain.Game;
//import domain.Player;
//import domain.ProposedWord;
//import jakarta.persistence.Query;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.boot.MetadataSources;
//import org.hibernate.boot.registry.StandardServiceRegistry;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//
//import java.lang.module.Configuration;
//import java.util.List;
//import java.util.Optional;
//
//public class GameRepositoryImpl implements GameRepository {
//    private static SessionFactory sessionFactory;
//
//    public GameRepositoryImpl() {
//        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
//        try {
//            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
//        } catch (Exception e) {
//            System.err.println("Exception " + e);
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
//    public void add(Game entity) {
//        try (Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//            session.save(entity);
//            session.getTransaction().commit();
//        } catch (Exception ex) {
//            System.out.println("Exception " + ex);
//        }
//    }
//
//    @Override
//    public void update(Game entity, Game newEntity) {
//        try (Session session = sessionFactory.openSession()){
//            Transaction tx = null;
//            try{
//                tx = session.beginTransaction();
//                Game game = session.load(Game.class, entity.getId());
//                game.setScore1(newEntity.getScore1());
//                game.setScore2(newEntity.getScore2());
//                game.setScore3(newEntity.getScore3());
//                tx.commit();
//
//            } catch (RuntimeException ex) {
//                System.err.println("Update error " + ex);
//                if (tx != null)
//                    tx.rollback();
//            }
//        }
//    }
//
//    @Override
//    public void delete(Integer integer) {
//
//    }
//
//    @Override
//    public Optional<Game> findByID(Integer integer) {
//        try (Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//            String sql = "from Game where id = :id";
//            List<Game> result = session.createQuery(sql, Game.class)
//                    .setParameter("id", integer).list();
//            for (Game game : result) {
//                return Optional.of(game);
//            }
//            session.getTransaction().commit();
//        }
//        return Optional.empty();
//    }
//
//    @Override
//    public List<Game> getAll() {
//        return null;
//    }
//
//    @Override
//    public Optional<Game> findLastGame() {
//        try (Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//            String sql = "from Game game where game.id = (select max(id) from Game)";
//            List<Game> result = session.createQuery(sql, Game.class).list();
//            for (Game game : result) {
//                return Optional.of(game);
//            }
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return Optional.empty();
//    }
//}

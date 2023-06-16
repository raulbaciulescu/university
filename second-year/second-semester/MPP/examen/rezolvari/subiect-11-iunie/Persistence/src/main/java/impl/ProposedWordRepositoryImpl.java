package impl;

import api.ProposedWordRepository;
import domain.ProposedWord;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProposedWordRepositoryImpl implements ProposedWordRepository {
    private final SessionFactory sessionFactory;

    public ProposedWordRepositoryImpl(SessionFactory sessionFactory) {
//        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
//        try {
//            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
//        }
//        catch (Exception e) {
//            System.err.println("Exception " + e);
//            StandardServiceRegistryBuilder.destroy(registry);
//        }
        this.sessionFactory = sessionFactory;
    }

    void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }


    @Override
    public void add(ProposedWord entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Exception " + ex);
        }
    }

    @Override
    public void update(ProposedWord entity, ProposedWord newEntity) {
        try (Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                ProposedWord pw = session.load(ProposedWord.class, entity.getId());
                pw.setPositions(newEntity.getPositions());
                tx.commit();

            } catch (RuntimeException ex) {
                System.err.println("Update error " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public Optional<ProposedWord> findByID(Integer integer) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            String sql = "from ProposedWord where id = :id";
            List<ProposedWord> result = session.createQuery(sql, ProposedWord.class)
                    .setParameter("id", integer).list();
            session.getTransaction().commit();
            for (ProposedWord pw : result)
                return Optional.of(pw);
        }
        return Optional.empty();
    }

    @Override
    public List<ProposedWord> getAll() {
        List<ProposedWord> result = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            result = session.createQuery("from ProposedWord", ProposedWord.class).list();
            session.getTransaction().commit();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public List<ProposedWord> findByGameId(Integer id) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            String sql = "from ProposedWord where gameId = :gameId";
            List<ProposedWord> result = session.createQuery(sql, ProposedWord.class)
                    .setParameter("gameId", id).list();
            session.getTransaction().commit();
            return result;
        }
    }
}

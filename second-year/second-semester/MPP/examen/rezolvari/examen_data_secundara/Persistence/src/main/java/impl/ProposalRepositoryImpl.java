package impl;

import api.ProposalRepository;
import domain.Proposal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProposalRepositoryImpl implements ProposalRepository {
    private static SessionFactory sessionFactory;

    public ProposalRepositoryImpl() {
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
    public void add(Proposal entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Exception " + ex);
        }
    }

    @Override
    public void update(Proposal entity, Proposal newEntity) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public Optional<Proposal> findByID(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<Proposal> getAll() {
        List<Proposal> result = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            result = session.createQuery("from Proposal", Proposal.class).list();
            session.getTransaction().commit();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}

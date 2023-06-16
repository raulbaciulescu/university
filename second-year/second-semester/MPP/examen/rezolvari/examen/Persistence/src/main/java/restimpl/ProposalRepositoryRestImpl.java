package restimpl;

import api.ProposalRepository;
import domain.Game;
import domain.Proposal;
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
public class ProposalRepositoryRestImpl implements ProposalRepository {
    private final SessionFactory sessionFactory;

    public ProposalRepositoryRestImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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


    @Override
    public List<Proposal> getGhicite(Integer gameId, Integer playerId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String sql = "from Proposal where gameId = :gameId and playerId = :playerId";
            List<Proposal> result = session.createQuery(sql, Proposal.class)
                    .setParameter("gameId", gameId).
                    setParameter("playerId", playerId).list();
            session.getTransaction().commit();
            return result;
        }
    }
}

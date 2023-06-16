package restimpl;


import api.ConfigurationRepository;
import domain.Configuration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ConfigurationRepositoryRestImpl implements ConfigurationRepository {
    private final SessionFactory sessionFactory;

    public ConfigurationRepositoryRestImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Configuration entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Exception " + ex);
        }
    }

    @Override
    public void update(Configuration entity, Configuration newEntity) {

    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public Optional<Configuration> findByID(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<Configuration> getAll() {
        List<Configuration> result = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            result = session.createQuery("from Configuration", Configuration.class).list();
            session.getTransaction().commit();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}


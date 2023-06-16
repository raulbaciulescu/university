package com.monitoringsystem.repository.impl;


import com.monitoringsystem.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import com.monitoringsystem.repository.api.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private static SessionFactory sessionFactory;

    public UserRepositoryImpl() {
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
    public void add(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(User entity, User newEntity) {
        //TODO
    }


    @Override
    public Optional<User> findByID(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                User user = session.createQuery("from User where id = :id", User.class)
                        .setParameter("id", id).setMaxResults(1).uniqueResult();
                session.delete(user);
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Eroare la stergere " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    @Override
    public List<User> getAll() {
        List<User> result;
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            result = session.createQuery("from User", User.class).list();
            session.getTransaction().commit();
        }
        return result;
    }

    @Override
    public Optional<User> findUser(User user) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            String sql = "from User where username = :username and password = :password";
            List<User> result = session.createQuery(sql, User.class)
                    .setParameter("username", user.getUsername()).setParameter("password", user.getPassword()).list();
            for (User userResult :  result) {
                return Optional.of(userResult);
            }
            session.getTransaction().commit();
        }
        return Optional.empty();
    }
}

package com.monitoringsystem.repository.impl;

import com.monitoringsystem.model.Status;
import com.monitoringsystem.model.Task;
import com.monitoringsystem.model.User;
import com.monitoringsystem.repository.api.TaskRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TaskRepositoryImpl implements TaskRepository {
    private static SessionFactory sessionFactory;

    public TaskRepositoryImpl() {
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
    public void add(Task task) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Task task, Task newTask) {
        //TODO
    }


    @Override
    public Optional<Task> findByID(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Task user = session.createQuery("from Task where id = :id", Task.class)
                        .setParameter("id", id).setMaxResults(1).uniqueResult();
                session.delete(user);
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Delete error" + ex);
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    @Override
    public List<Task> getAll() {
        List<Task> result;
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            result = session.createQuery("from Task", Task.class).list();
            session.getTransaction().commit();
        }
        return result;
    }

    @Override
    public List<Task> getSpecificTasks(Long userId) {
        List<Task> result;
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            String sql = "from Task where employeeId = :userId";
            result = session.createQuery(sql, Task.class).setParameter("userId", userId).list();
            session.getTransaction().commit();
        }
        return result;
    }

    @Override
    public void finishTask(Long id) {
        try (Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try{
                tx = session.beginTransaction();
                Task task = session.load(Task.class, id);
                task.setStatus(Status.FINISHED);
                tx.commit();

            } catch (RuntimeException ex) {
                System.err.println("Update error " + ex);
                if (tx != null)
                    tx.rollback();
            }
        }

    }


}

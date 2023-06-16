package user.annotations;

import event.hbm.Event;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Date;
import java.util.List;

public class MainUser {
    private static SessionFactory sessionFactory;

    static void initialize() {
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

    public static void main(String ... arg) {
        initialize();

        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(new UserAnnot(13L, "testorm", "pass123"));
            session.getTransaction().commit();
        }


        try(Session  session = sessionFactory.openSession()){
            session.beginTransaction();
            List<UserAnnot> result = session.createQuery("from UserAnnot", UserAnnot.class).list();
            for (UserAnnot userAnnot :  result) {
                System.out.println(userAnnot.getUsername() + " " + userAnnot.getPassword());
            }
            session.getTransaction().commit();
        }

        close();
    }
}

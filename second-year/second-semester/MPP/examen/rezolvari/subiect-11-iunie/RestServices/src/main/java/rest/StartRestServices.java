package rest;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;
import java.util.Properties;


@CrossOrigin
@SpringBootApplication()
@ComponentScan(value = {"api", "impl", "rest"})
public class StartRestServices {
    public static void main(String[] args) {
        SpringApplication.run(StartRestServices.class, args);
    }

    //cumva repo-ul isi ia automat proprietatile astea
    @Primary
    @Bean
    public Properties getProps() {
        Properties props = new Properties();
        try {
            props.load(StartRestServices.class.getResourceAsStream("/application.properties"));
            System.out.println("Server properties set. ");
            props.list(System.out);

        } catch (IOException e) {
            System.err.println("Configuration file exam.properties not found " + e);
        }
        return props;
    }

    @Primary
    @Bean
    public SessionFactory getSession(){
        SessionFactory sessionFactory=null;
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            System.err.println("Exception "+e);
            StandardServiceRegistryBuilder.destroy( registry );
        }
        return sessionFactory;
    }
}





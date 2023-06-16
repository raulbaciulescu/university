
import exam.networking.utils.AbstractServer;
import exam.networking.utils.ExamRpcConcurrentServer;
import exam.networking.utils.ServerException;
import exam.server.ExamServicesImpl;
import exam.services.IExamServices;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.IOException;
import java.util.Properties;

public class StartRpcServer {
    private static int defaultPort=55554;
    static SessionFactory sessionFactory;
    static void initialize() {
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
    }

    static void close(){
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }

    }

    public static void main(String[] args) {
        Properties serverProps=new Properties();
        try{
            serverProps.load(StartRpcServer.class.getResourceAsStream("/model.properties"));
            System.out.println("Server properties set. ");
            serverProps.list(System.out);
        } catch (IOException exception) {
            System.err.println("Cannot find model.properties "+exception);
            return;
        }
        initialize();
        /*IGameRepository repoGames=new GameDbRepository(serverProps);
        IConfigurationRepository repoConf=new ConfigurationDbRepository(sessionFactory);
        IPlayerRepository repoPlayers=new PlayerDbRepository(serverProps);
        */
        IExamServices serverImpl =new ExamServicesImpl();

        int serverPort =defaultPort;
        try {
            serverPort = Integer.parseInt(serverProps.getProperty("exam.server.port"));
        }catch (NumberFormatException nef){
            System.err.println("Wrong  Port Number "+nef.getMessage());
            System.err.println("Using default port "+defaultPort);
        }
        System.out.println("Starting server on port: "+ serverPort);
        //abstract Server- punem implementarea de pe server a serviceului
        AbstractServer server=new ExamRpcConcurrentServer(serverPort, serverImpl);
        try{
            server.start();
        } catch (ServerException e) {
            System.err.println("Error starting the server" + e.getMessage());

        }finally {
            try {
                server.stop();
            }catch(ServerException e){
                System.err.println("Error stopping server "+e.getMessage());
            }
        }
        close();

    }
}

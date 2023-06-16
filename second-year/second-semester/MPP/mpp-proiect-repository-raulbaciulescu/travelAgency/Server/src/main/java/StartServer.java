import api.FlightRepository;
import api.LocationRepository;
import api.UserRepository;
import domain.User;
import impl.FlightRepositoryImpl;
import impl.LocationRepositoryImpl;
import impl.PurchaseRepositoryImpl;
import impl.UserRepositoryImpl;
import impl.database.TableFactory;
import server.ServiceImpl;
import services.Service;
import utils.AbstractServer;
import utils.RpcConcurrentServer;

import java.io.IOException;
import java.rmi.ServerException;
import java.sql.SQLException;
import java.util.Properties;

public class StartServer {
    private static final int defaultPort = 55555;
    public static void main(String[] args) throws SQLException {
        Properties serverProps = new Properties();
        try {
            serverProps.load(StartServer.class.getResourceAsStream("/server.properties"));
            System.out.println("Server properties set. ");
            serverProps.list(System.out);
        } catch (IOException e) {
            System.err.println("Cannot find server.properties " + e);
            return;
        }
        TableFactory factory = new TableFactory();
        UserRepository userRepository = new UserRepositoryImpl(factory);
        LocationRepository locationRepository = new LocationRepositoryImpl(factory);
        FlightRepository flightRepository = new FlightRepositoryImpl(locationRepository, factory);
        PurchaseRepositoryImpl purchaseRepository = new PurchaseRepositoryImpl(factory, flightRepository);

        Service service = new ServiceImpl(userRepository, flightRepository, purchaseRepository);

        int serverPort = defaultPort;
        try {
            serverPort = Integer.parseInt(serverProps.getProperty("server.port"));
        } catch (NumberFormatException nef) {
            System.err.println("Wrong  Port Number" + nef.getMessage());
            System.err.println("Using default port " + defaultPort);
        }
        System.out.println("Starting server on port: " + serverPort);
        AbstractServer server = new RpcConcurrentServer(serverPort, service);

        try {
            server.start();
        } catch (ServerException e) {
            System.err.println("Error starting the server" + e.getMessage());
        } finally {
            try {
                server.stop();
            } catch (ServerException e) {
                System.err.println("Error stopping server " + e.getMessage());
            }
        }
    }
}

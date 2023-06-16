import api.FlightRepository;
import api.LocationRepository;
import api.UserRepository;
import impl.FlightRepositoryImpl;
import impl.LocationRepositoryImpl;
import impl.PurchaseRepositoryImpl;
import impl.UserRepositoryImpl;
import impl.database.TableFactory;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;
import java.sql.SQLException;

public class GrpcServer {
    public static void main(String[] args) throws SQLException {


        TableFactory factory = new TableFactory();
        UserRepository userRepository = new UserRepositoryImpl(factory);
        LocationRepository locationRepository = new LocationRepositoryImpl(factory);
        FlightRepository flightRepository = new FlightRepositoryImpl(locationRepository, factory);
        PurchaseRepositoryImpl purchaseRepository = new PurchaseRepositoryImpl(factory, flightRepository);

        ServiceImpl service = new ServiceImpl(userRepository, flightRepository, purchaseRepository);

        Server server = ServerBuilder.forPort(9999).addService(service).build();
        try {
            server.start();
            System.out.println("Server started at: " + server.getPort());
            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

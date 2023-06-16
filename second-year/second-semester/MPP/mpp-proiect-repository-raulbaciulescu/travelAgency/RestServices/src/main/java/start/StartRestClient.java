package start;

import api.LocationRepository;
import domain.Flight;
import domain.Location;
import impl.LocationRepositoryImpl;
import impl.database.TableFactory;
import org.springframework.web.client.RestClientException;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class StartRestClient {
    private final static FlightClient flightClient = new FlightClient();


    public static void main(String[] args) throws SQLException {
        LocationRepository locationRepository = new LocationRepositoryImpl(new TableFactory());
        Location l1 = locationRepository.getAll().get(0);
        Location l2 = locationRepository.getAll().get(1);
        Flight flight = new Flight(100L, l1, l2, LocalDateTime.now(), 50);
        Flight flightUpdated = new Flight(100L, l1, l2, LocalDateTime.now(), 10000);
        try {
            show(() -> System.out.println(flightClient.add(flight)));
            show(() -> {
                Flight[] flights = flightClient.getAll();
                for(Flight f : flights) {
                    System.out.println(f);
                }
            });
            show(() -> System.out.println(flightClient.findById(100L)));
            show(() -> flightClient.update(flightUpdated));
            show(() -> flightClient.delete(100L));

        } catch(RestClientException ex) {
            System.out.println("Exception ... " + ex.getMessage());
        }

    }



    private static void show(Runnable task) {
        try {
            task.run();
        } catch (RestClientException e) {
            System.out.println("Service exception"+ e);
        }
    }

}

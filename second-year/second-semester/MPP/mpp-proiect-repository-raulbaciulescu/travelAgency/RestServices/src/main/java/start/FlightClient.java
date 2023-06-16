package start;

import domain.Flight;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

public class FlightClient {
    public static final String URL = "http://localhost:8080/flight";
    private final RestTemplate restTemplate = new RestTemplate();

    private <T> T execute(Callable<T> callable) {
        try {
            return callable.call();
        } catch (ResourceAccessException | HttpClientErrorException e) { // server down, resource exception
            throw new RestException(e);
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

    public Flight[] getAll() {
        return execute(() -> restTemplate.getForObject(URL, Flight[].class));
    }

    public Flight findById(Long id) {
        return execute(() -> restTemplate.getForObject(String.format("%s/%d", URL, id), Flight.class));
    }

    public Flight add(Flight flight) {
        return execute(() -> restTemplate.postForObject(URL, flight, Flight.class));
    }

    public void update(Flight flight) {
        execute(() -> {
            restTemplate.put(String.format("%s/%s", URL, flight.getId()), flight);
            return null;
        });
    }

    public void delete(long id) {
        execute(() -> {
            restTemplate.delete(String.format("%s/%s", URL, id));
            return null;
        });
    }
}

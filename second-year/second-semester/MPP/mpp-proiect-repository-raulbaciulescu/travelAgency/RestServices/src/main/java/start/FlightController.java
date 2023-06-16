package start;


import api.FlightNewRepository;
import api.FlightRepository;
import domain.Flight;
import domain.FlightNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/flight")
public class FlightController {
    private static final String template = "Hello, %s!";
    @Autowired
    private FlightNewRepository flightRepository;

    @RequestMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        System.out.println("hello");
        return String.format(template, name);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<FlightNew> getAll(){
        System.out.println("Get all flights ...");
        return flightRepository.getAll();
    }

    @RequestMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) throws SQLException {
        System.out.println("Find by id ...");
        Optional<FlightNew> optionalFlight = flightRepository.findByID(id);
        if (optionalFlight.isPresent()) {
            return new ResponseEntity<>(optionalFlight.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Flight not found", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.POST)
    public FlightNew add(@RequestBody FlightNew flight){
        System.out.println("Add flight ...");
        System.out.println(flight);
        flightRepository.add(flight);
        return flight;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public FlightNew update(@RequestBody FlightNew flight) {
        System.out.println("Updating flight ...");
        flightRepository.update(flight, flight);
        return flight;
    }

    @RequestMapping(value="/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Integer id){
        System.out.println("Deleting flight with id ... " + id);
        try {
            flightRepository.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>("Flight not found", HttpStatus.NOT_FOUND);
        }
    }
}

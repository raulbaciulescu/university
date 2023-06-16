package server;

import api.FlightRepository;
import api.PurchaseRepository;
import api.UserRepository;
import domain.Flight;
import domain.Purchase;
import domain.User;
import domain.dto.PurchaseDto;
import services.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServiceImpl implements Service {
    private final UserRepository userRepository;
    private final FlightRepository flightRepository;
    private final PurchaseRepository purchaseRepository;
    private final Map<Long, Observer> loggedUsers;
    private final Random random;

    public ServiceImpl(UserRepository userRepository, FlightRepository flightRepository, PurchaseRepository purchaseRepository) {
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
        this.purchaseRepository = purchaseRepository;
        this.loggedUsers = new ConcurrentHashMap<>();
        this.random = new Random();
    }

    @Override
    public synchronized void login(User user, Observer client) throws LoginException {
        Optional<User> userOptional = userRepository.findUser(user.getUsername(), user.getPassword());
        if (userOptional.isPresent()) {
            loggedUsers.put(userOptional.get().getId(), client);
        }
        else
            throw new LoginException("Authentication failed!");
    }


    @Override
    public synchronized void purchase(Purchase purchase) throws PurchaseException {
        Flight flight = purchase.getFlight();
        try {
            flight = flightRepository.findByID(purchase.getFlight().getId()).get();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (flight.getNrOfSeats() < purchase.getNrOfSeats()) {
            throw new PurchaseException("not enough nr of seats!");
        }
        Flight flightNew = new Flight(flight.getStart(), flight.getDestination(), flight.getStartDate(),
                flight.getNrOfSeats() - purchase.getNrOfSeats());
        flightNew.setId(flight.getId());
        flightRepository.update(flight, flightNew);

        purchase.setId(random.nextLong());

        Purchase purchase2 = new Purchase(flight, purchase.getClientName(), purchase.getClientAddress(),
                purchase.getTourists(), purchase.getNrOfSeats());
        purchase2.setId(purchase.getId());
        purchaseRepository.add(purchase2);
        notifyUsers(flightNew);
    }


    public synchronized void notifyUsers(Flight flight) {
        int defaultThreadsNo = 5;
        ExecutorService executor= Executors.newFixedThreadPool(defaultThreadsNo);
        for (Observer observer: loggedUsers.values()) {
            executor.execute(() -> {
                System.out.println("Flight update in serviceimpl!!: " + flight + " " + observer);
                observer.updateFlight(flight);
            });
        }
        executor.shutdown();
    }

    @Override
    public synchronized void logout(User user, Observer client) throws LoginException {
        Observer client1 = loggedUsers.remove(userRepository.findUser(user.getUsername(), user.getPassword()).get().getId());
        if (client1 == null) {
            throw new LoginException("User " + user.getUsername() + " is not logged in.");
        }
    }

    @Override
    public synchronized List<Flight> getFlights() {
        return flightRepository.getAll();
    }
}

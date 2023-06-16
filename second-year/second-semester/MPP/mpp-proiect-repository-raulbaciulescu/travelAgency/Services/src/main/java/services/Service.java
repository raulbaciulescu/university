package services;

import domain.Flight;
import domain.Purchase;
import domain.User;
import domain.dto.PurchaseDto;

import java.util.List;

public interface Service {
    void login(User user, Observer client) throws LoginException;
    void purchase(Purchase purchase) throws PurchaseException;
    void logout(User user, Observer client) throws LoginException;
    List<Flight> getFlights() throws FlightException;
}

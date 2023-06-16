package services;

import domain.Flight;

public interface Observer {
    void updateFlight(Flight flight);
}

package utils;

import domain.Flight;
import domain.User;

import java.sql.SQLException;

public class Resources {
    static Resources instance = null;
    private User currentUser;
    private Flight selectedFlight;

    private Resources() {

    }
    public static Resources getInstance() throws SQLException {
        if (instance == null)
            instance = new Resources();
        return instance;
    }

    public void loginCurrentUser(User user) {
        currentUser = user;
    }

    public void logoutCurrentUser() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setFlight(Flight flight) {
        selectedFlight = flight;
    }

    public Flight getSelectedFlight() {
        return selectedFlight;
    }
}

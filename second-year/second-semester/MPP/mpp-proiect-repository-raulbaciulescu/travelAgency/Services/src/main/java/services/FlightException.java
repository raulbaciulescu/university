package services;

public class FlightException extends Exception {
    public FlightException() {
    }

    public FlightException(String message) {
        super(message);
    }

    public FlightException(String message, Throwable cause) {
        super(message, cause);
    }
}

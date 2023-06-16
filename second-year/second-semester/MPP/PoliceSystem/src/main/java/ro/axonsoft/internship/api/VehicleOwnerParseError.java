package ro.axonsoft.internship.api;

public interface VehicleOwnerParseError {

    /**
     * Numărul liniei la care a apărut eroarea.
     */
    Integer getLine();

    /**
     * 0 - invalid line, 1 - invalid CI, 2 - invalid date
     */
    Integer getType();
}
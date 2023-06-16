package ro.axonsoft.internship.api;

public interface RoIdCardParser {
    RoIdCardProperties parseIdCard(String idCard) throws InvalidRoIdCardException, InvalidRoIdCardSeriesException;
}

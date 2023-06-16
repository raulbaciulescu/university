package domain.dto;
import domain.Entity;

import java.io.Serializable;
import java.util.List;

public class PurchaseDto extends Entity<Long> implements Serializable {
    private Long flightId;
    private String clientName;
    private String clientAddress;
    private List<String> tourists;
    private int nrOfSeats;

    public PurchaseDto(Long flightId, String clientName, String clientAddress, List<String> tourists, int nrOfSeats) {
        this.flightId = flightId;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.tourists = tourists;
        this.nrOfSeats = nrOfSeats;
    }

    public Long getFlightId() {
        return flightId;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public List<String> getTourists() {
        return tourists;
    }

    public int getNrOfSeats() {
        return nrOfSeats;
    }
}

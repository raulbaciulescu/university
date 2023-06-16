package domain.dto;


import domain.Entity;

import java.time.LocalDateTime;

public class FlightDto extends Entity<Long> {
    private final long startId;
    private final long destinationId;
    private final LocalDateTime startDate;
    private final int nrOfSeats;

    public FlightDto(long startId, long destinationId, LocalDateTime startDate, int nrOfSeats) {
        this.startId = startId;
        this.destinationId = destinationId;
        this.startDate = startDate;
        this.nrOfSeats = nrOfSeats;
    }

    public long getStartId() {
        return startId;
    }

    public long getDestinationId() {
        return destinationId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public int getNrOfSeats() {
        return nrOfSeats;
    }
}


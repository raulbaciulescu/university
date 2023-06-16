package domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@jakarta.persistence.Entity
@Table( name = "flight_new")
public class FlightNew extends Entity<Integer> {
    private String start;
    private String destination;
    private Date startDate;
    private Integer nrOfSeats;

    public FlightNew(String start, String destination, Date startDate, int nrOfSeats) {
        this.start = start;
        this.destination = destination;
        this.startDate = startDate;
        this.nrOfSeats = nrOfSeats;
    }

    public FlightNew() {

    }

    public FlightNew(Integer id, String start, String destination, Date startDate, int nrOfSeats) {
        this.setId(id);
        this.start = start;
        this.destination = destination;
        this.startDate = startDate;
        this.nrOfSeats = nrOfSeats;
    }

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return super.getId();
    }

    @Override
    public void setId(Integer integer) {
        super.setId(integer);
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getNrOfSeats() {
        return nrOfSeats;
    }

    public void setNrOfSeats(Integer nrOfSeats) {
        this.nrOfSeats = nrOfSeats;
    }

    @Override
    public String toString() {
        return "FlightNew{" +
                "id='" + getId() + '\'' +
                "start='" + start + '\'' +
                ", destination='" + destination + '\'' +
                ", startDate=" + startDate +
                ", nrOfSeats=" + nrOfSeats +
                '}';
    }
}
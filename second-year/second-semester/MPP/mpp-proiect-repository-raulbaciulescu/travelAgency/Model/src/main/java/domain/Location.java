package domain;

import java.io.Serializable;

public class Location extends Entity<Long> implements Serializable {
    private String name;
    private String airport;

    public Location(Long id, String name, String airport) {
        this.setId(id);
        this.name = name;
        this.airport = airport;
    }

    public Location() {

    }

    public Location(String name, String airport) {
        this.name = name;
        this.airport = airport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    @Override
    public String toString() {
        return name + " " + airport;
    }
}

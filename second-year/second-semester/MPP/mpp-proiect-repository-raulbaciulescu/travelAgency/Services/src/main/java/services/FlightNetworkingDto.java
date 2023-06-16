package services;

import java.io.Serializable;

public class FlightNetworkingDto implements Serializable {
    Long id;
    Integer newValue;

    public FlightNetworkingDto(Long id, Integer newValue) {
        this.id = id;
        this.newValue = newValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNewValue() {
        return newValue;
    }

    public void setNewValue(Integer newValue) {
        this.newValue = newValue;
    }

}

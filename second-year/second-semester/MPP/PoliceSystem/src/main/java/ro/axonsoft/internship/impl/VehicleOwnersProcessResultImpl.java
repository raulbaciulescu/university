package ro.axonsoft.internship.impl;

import ro.axonsoft.internship.api.Judet;
import ro.axonsoft.internship.api.VehicleOwnerParseError;
import ro.axonsoft.internship.api.VehicleOwnersProcessResult;

import java.io.Serializable;
import java.util.*;

public class VehicleOwnersProcessResultImpl implements VehicleOwnersProcessResult, Serializable {
    private Integer oddToEvenRatio;
    private Map<Judet, Integer> unregCarsCountByJud;
    private Integer passedRegChangeDueDate;
    private Set<VehicleOwnerParseError> errors;

    public VehicleOwnersProcessResultImpl() {
        this.oddToEvenRatio = 0;
        this.passedRegChangeDueDate = 0;
        this.errors = new HashSet<>();
        this.unregCarsCountByJud = new EnumMap<Judet, Integer>(Judet.class);
    }

    @Override
    public Integer getOddToEvenRatio() {
        return oddToEvenRatio;
    }

    @Override
    public Map<Judet, Integer> getUnregCarsCountByJud() {
        return unregCarsCountByJud;
    }

    @Override
    public Integer getPassedRegChangeDueDate() {
        return passedRegChangeDueDate;
    }

    @Override
    public Set<VehicleOwnerParseError> getErrors() {
        return errors;
    }

    public void addToErrors(VehicleOwnerParseError vehicleOwnerParseError) {
        errors.add(vehicleOwnerParseError);
    }

    public void setOddToEvenRatio(Integer oddToEvenRatio) {
        this.oddToEvenRatio = oddToEvenRatio;
    }

    public void setPassedRegChangeDueDate(Integer passedRegChangeDueDate) {
        this.passedRegChangeDueDate = passedRegChangeDueDate;
    }

    public void setUnregCarsCountByJud(Map<Judet, Integer> unregCarsCountByJud) {
        this.unregCarsCountByJud = unregCarsCountByJud;
    }

    @Override
    public String toString() {
        return "VehicleOwnersProcessResultImpl{" +
                "oddToEvenRatio=" + oddToEvenRatio +
                ", unregCarsCountByJud=" + unregCarsCountByJud +
                ", passedRegChangeDueDate=" + passedRegChangeDueDate +
                ", errors=" + errors +
                '}';
    }
}

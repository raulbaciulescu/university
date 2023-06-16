package ro.axonsoft.internship.impl;

import ro.axonsoft.internship.api.VehicleOwnerParseError;

import java.io.Serializable;

public class VehicleParseErrorImpl implements VehicleOwnerParseError, Serializable {
    private final Integer line;
    private final Integer type;

    public VehicleParseErrorImpl(Integer line, Integer type) {
        this.line = line;
        this.type = type;
    }

    @Override
    public Integer getLine() {
        return line;
    }

    @Override
    public Integer getType() {
        return type;
    }

    @Override
    public String toString() {
        return "error line: " + line + " " + type + "\n";
    }
}

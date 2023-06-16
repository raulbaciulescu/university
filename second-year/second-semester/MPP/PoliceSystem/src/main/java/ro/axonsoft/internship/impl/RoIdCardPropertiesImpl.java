package ro.axonsoft.internship.impl;

import ro.axonsoft.internship.api.Judet;
import ro.axonsoft.internship.api.RoIdCardProperties;


/**
 * implementation for RoIdCardProperties
 */
public class RoIdCardPropertiesImpl implements RoIdCardProperties {
    private final Judet judet;
    private final String series;
    private final Integer number;

    public RoIdCardPropertiesImpl(Judet judet, String series, Integer number) {
        this.judet = judet;
        this.series = series;
        this.number = number;
    }

    @Override
    public Judet getJudet() {
        return judet;
    }

    @Override
    public String getSeries() {
        return series;
    }

    @Override
    public Integer getNumber() {
        return number;
    }
}

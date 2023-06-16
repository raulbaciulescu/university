package ro.axonsoft.internship.impl;

import ro.axonsoft.internship.api.Judet;
import ro.axonsoft.internship.api.RoRegPlateProperties;

public class RoRegPlatePropertiesImpl implements RoRegPlateProperties {
    private final Judet judet;
    private final Short digits;
    private final String letters;

    public RoRegPlatePropertiesImpl(Judet judet, Short digits, String letters) {
        this.judet = judet;
        this.digits = digits;
        this.letters = letters;
    }

    @Override
    public Judet getJudet() {
        return judet;
    }

    @Override
    public Short getDigits() {
        return digits;
    }

    @Override
    public String getLetters() {
        return letters;
    }
}

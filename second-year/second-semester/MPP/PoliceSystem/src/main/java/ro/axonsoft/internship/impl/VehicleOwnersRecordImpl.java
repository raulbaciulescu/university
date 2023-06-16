package ro.axonsoft.internship.impl;

import ro.axonsoft.internship.api.RoIdCardProperties;
import ro.axonsoft.internship.api.RoRegPlateProperties;
import ro.axonsoft.internship.api.VehicleOwnerRecord;

import java.util.Date;

public class VehicleOwnersRecordImpl implements VehicleOwnerRecord {
    private final RoRegPlateProperties regPlateProperties;
    private final RoIdCardProperties idCardProperties;
    private final Date date;

    public VehicleOwnersRecordImpl(RoRegPlateProperties regPlateProperties, RoIdCardProperties idCardProperties, Date date) {
        this.regPlateProperties = regPlateProperties;
        this.idCardProperties = idCardProperties;
        this.date = date;
    }

    @Override
    public RoIdCardProperties getIdCard() {
        return idCardProperties;
    }

    @Override
    public RoRegPlateProperties getRegPlate() {
        return regPlateProperties;
    }

    @Override
    public Date getIdCardIssueDate() {
        return date;
    }
}

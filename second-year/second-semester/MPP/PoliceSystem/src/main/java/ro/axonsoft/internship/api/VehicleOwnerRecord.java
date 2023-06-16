package ro.axonsoft.internship.api;

import java.util.Date;

public interface VehicleOwnerRecord {
    RoIdCardProperties getIdCard();
    RoRegPlateProperties getRegPlate();
    Date getIdCardIssueDate();
}

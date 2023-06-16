package ro.axonsoft.internship.impl;

import jdk.swing.interop.SwingInterOpUtils;
import ro.axonsoft.internship.api.Judet;
import ro.axonsoft.internship.api.RoIdCardProperties;
import ro.axonsoft.internship.api.RoRegPlateProperties;
import ro.axonsoft.internship.api.VehicleOwnerRecord;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class VehicleOwnersAggregator {
    private Date referenceDate;
    private Integer oddNumber;
    private Integer evenNumber;
    private Map<Judet, Integer> unregCarsCountByJud;
    private Integer passedRegChangeDueDate;
    private Integer oddToEvenRatio;


    public VehicleOwnersAggregator(Date referenceDate) {
        this.referenceDate = referenceDate;
        oddNumber = 0;
        evenNumber = 0;
        this.unregCarsCountByJud = new EnumMap<Judet, Integer>(Judet.class);
        for (Judet judet : Judet.values()) {
            unregCarsCountByJud.put(judet, 0);
            passedRegChangeDueDate = 0;
        }
    }

    public Integer getOddToEvenRatio() {
        double d = oddNumber * 1.0 / evenNumber * 100;
        BigDecimal bd = BigDecimal.valueOf(d);
        bd = bd.setScale(0, RoundingMode.HALF_UP);
        return bd.intValue();
    }

    public Map<Judet, Integer> getUnregCarsCountByJud() {
        return unregCarsCountByJud;
    }

    public Integer getPassedRegChangeDueDate() {
        return passedRegChangeDueDate;
    }

    /**
     * Compute how many unregistered numbers are, how many odd and even numbers are,
     * the number of vehicles owned by persons domiciled in one county and the vehicle registered in another
     * county and more than 30 days have elapsed since the date of issue of the identity card
     * @param vehicleOwnerRecord a class with date of issue of the identity card, number of plate, identity card
     * @return current instance
     */
    VehicleOwnersAggregator aggregate(VehicleOwnerRecord vehicleOwnerRecord) {
        RoIdCardProperties idCardProperties = vehicleOwnerRecord.getIdCard();
        RoRegPlateProperties regPlateProperties = vehicleOwnerRecord.getRegPlate();
        Date date = vehicleOwnerRecord.getIdCardIssueDate();

        //unregistered number plate
        if (regPlateProperties == null) {
            unregCarsCountByJud.put(idCardProperties.getJudet(), unregCarsCountByJud.get(idCardProperties.getJudet()) + 1);
            return this;
        }

        //if he has changed his address and 30 days have passed
        LocalDate referenceDateLocal = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(referenceDate) );;
        LocalDate dateLocal = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(date) );;
        int days = (int) Duration.between(referenceDateLocal.atStartOfDay(), dateLocal.atStartOfDay()).toDays();
        if (idCardProperties.getJudet() != regPlateProperties.getJudet() && days >= 30) {
            passedRegChangeDueDate++;
        }

        //odd or even
        if (regPlateProperties.getDigits() % 2 == 0)
            evenNumber++;
        else
            oddNumber++;
        return this;
    }
}

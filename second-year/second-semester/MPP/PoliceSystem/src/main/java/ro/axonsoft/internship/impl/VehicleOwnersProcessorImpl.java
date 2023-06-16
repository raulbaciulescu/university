package ro.axonsoft.internship.impl;

import ro.axonsoft.internship.api.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class VehicleOwnersProcessorImpl implements VehicleOwnersProcessor {
    private final RoIdCardParser idCardParser;
    private final RoRegPlateParser regPlateParser;
    private final Date referenceDate;
    private VehicleOwnersAggregator aggregator;
    private VehicleOwnersProcessResultImpl result;


    public VehicleOwnersProcessorImpl(RoIdCardParser idCardParser, RoRegPlateParser regPlateParser, Date referenceDate) {
        this.idCardParser = idCardParser;
        this.regPlateParser = regPlateParser;
        this.referenceDate = referenceDate;
        this.aggregator = new VehicleOwnersAggregator(referenceDate);
        this.result = new VehicleOwnersProcessResultImpl();
    }

    /**
     * Convert a string with date in Date
     * @param dateWithSpaces
     *         - a string with the date
     * @return
     *         - date object
     * @throws ParseException
     *         - if the date is not valid
     */
    public Date getDate(String dateWithSpaces) throws ParseException {
        String dateString = dateWithSpaces.replaceAll(" ", "");
        SimpleDateFormat sdfrmt = new SimpleDateFormat("yyyy-MM-dd");
        sdfrmt.setLenient(false);
        return sdfrmt.parse(dateString);
    }

    /**
     * Processes the csv file line by line
     * @param ciCarRegNbInputStream
     *            - stream for serializing input data
     * @param processResultOutputStream
     *            - stream for serializing output data
     * @throws IOException
     *            - if the named file does not exist, is a directory rather than a regular file,
     *  or for some other reason cannot be opened for reading.
     */
    @Override
    public void process(InputStream ciCarRegNbInputStream, ObjectOutputStream processResultOutputStream) throws IOException {
        Scanner scanner = new Scanner(ciCarRegNbInputStream);
        int lineContor = 0;

        while (scanner.hasNextLine()) {
            lineContor++;
            String line = scanner.nextLine();
            List<String> linesSplit = List.of(line.split(";"));
            if (linesSplit.size() >= 3) {
                try {
                    RoIdCardProperties idCardProperties = idCardParser.parseIdCard(linesSplit.get(0));
                    RoRegPlateProperties regPlateProperties = regPlateParser.parseRegistrationPlate(linesSplit.get(2));
                    Date date = getDate(linesSplit.get(1));
                    VehicleOwnerRecord record = new VehicleOwnersRecordImpl(regPlateProperties,idCardProperties, date);
                    aggregator = aggregator.aggregate(record);
                } catch (InvalidRoIdCardSeriesException | InvalidRoIdCardException e1) {
                    result.addToErrors(new VehicleParseErrorImpl(lineContor, 1));
                } catch (ParseException e) {
                    result.addToErrors(new VehicleParseErrorImpl(lineContor, 2));
                }
            }
            else
                result.addToErrors(new VehicleParseErrorImpl(lineContor, 0));
        }

        result.setOddToEvenRatio(aggregator.getOddToEvenRatio());
        result.setPassedRegChangeDueDate(aggregator.getPassedRegChangeDueDate());
        result.setUnregCarsCountByJud(aggregator.getUnregCarsCountByJud());
        processResultOutputStream.writeObject(result);
    }
}

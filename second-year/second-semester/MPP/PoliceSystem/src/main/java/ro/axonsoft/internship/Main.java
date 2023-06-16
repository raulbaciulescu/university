package ro.axonsoft.internship;

import ro.axonsoft.internship.api.RoIdCardParser;
import ro.axonsoft.internship.api.RoIdCardSeriesJudMapper;
import ro.axonsoft.internship.api.RoRegPlateParser;
import ro.axonsoft.internship.impl.RoIdCardParserImpl;
import ro.axonsoft.internship.impl.RoIdCardSeriesJudJudMapperImpl;
import ro.axonsoft.internship.impl.RoRegPlateParseImpl;
import ro.axonsoft.internship.impl.VehicleOwnersProcessorImpl;

import java.io.*;
import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        try {
            InputStream input = new FileInputStream("src/main/in.csv");
            FileOutputStream fileOut = new FileOutputStream("src/main/result.ser");
            ObjectOutputStream output = new ObjectOutputStream(fileOut);
            Date referenceDate = Date.valueOf("2005-05-12");
            RoIdCardSeriesJudMapper mapper = new RoIdCardSeriesJudJudMapperImpl();
            RoIdCardParser idCardParser = new RoIdCardParserImpl(mapper);
            RoRegPlateParser roRegPlateParser = new RoRegPlateParseImpl();
            VehicleOwnersProcessorImpl processor = new VehicleOwnersProcessorImpl(idCardParser, roRegPlateParser, referenceDate);
            processor.process(input, output);

            input.close();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}

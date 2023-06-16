package ro.axonsoft.internship.impl;

import org.apache.commons.lang3.EnumUtils;
import ro.axonsoft.internship.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoRegPlateParseImpl implements RoRegPlateParser {
    /**
     * Check if a string is a valid plate number
     * ms 11 bzn = correct
     * ms 11 BZN = incorrect
     * MS 11 BZN = correct
     * ms11bzn = correct
     * b 111 aaa = correct
     * b 11 aaa = correct
     * @param registrationPlate a string with county, numbers and letters of a plate
     * @return  an entity with all information for number plate
     */
    @Override
    public RoRegPlateProperties parseRegistrationPlate(String registrationPlate) {
        List<Pattern> patternList = new ArrayList<>();
        patternList.add(Pattern.compile("^\s*([a-z]{2})\s*([0-9]{2})\s*([a-z]{3})\s*$"));
        patternList.add(Pattern.compile("^\s*([A-Z]{2})\s*([0-9]{2})\s*([A-Z]{3})\s*$"));
        patternList.add(Pattern.compile("^\s*(B)\s*([0-9]{2,3})\s*([A-Z]{3})\s*$"));
        patternList.add(Pattern.compile("^\s*(b)\s*([0-9]{2,3})\s*([a-z]{3})\s*$"));
        for (Pattern pattern : patternList) {
            Matcher m = pattern.matcher(registrationPlate);
            if (m.find()) {
                if (EnumUtils.isValidEnum(Judet.class, m.group(1).toUpperCase(Locale.ROOT)))
                    return new RoRegPlatePropertiesImpl(Judet.valueOf(m.group(1).toUpperCase(Locale.ROOT)),
                            Short.parseShort(m.group(2)), m.group(3));
            }
        }
        return null;
    }
}

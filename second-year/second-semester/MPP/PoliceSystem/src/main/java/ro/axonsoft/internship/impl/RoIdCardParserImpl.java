package ro.axonsoft.internship.impl;

import ro.axonsoft.internship.api.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoIdCardParserImpl implements RoIdCardParser {
    private final RoIdCardSeriesJudMapper idCardSeriesJudMapper;


    public RoIdCardParserImpl(RoIdCardSeriesJudMapper idCardSeriesJudMapper) {
        this.idCardSeriesJudMapper = idCardSeriesJudMapper;
    }

    /**
     *  Check if a string is a valid id card
     *
     * @param idCard a string with series, number of identity card
     * @return an entity with all information for identity card
     * @throws InvalidRoIdCardSeriesException
     */
    @Override
    public RoIdCardProperties parseIdCard(String idCard) throws InvalidRoIdCardSeriesException {
        Pattern p1 = Pattern.compile("^\s*([a-z]{2}|[A-Z]{2})\s*([0-9]{6})\s*$");
        Matcher m = p1.matcher(idCard);
        if (m.find()) {
            return new RoIdCardPropertiesImpl(idCardSeriesJudMapper.mapIdCardToJud(m.group(1)), m.group(1), Integer.parseInt(m.group(2)));
        }
        throw new InvalidRoIdCardSeriesException();
    }
}

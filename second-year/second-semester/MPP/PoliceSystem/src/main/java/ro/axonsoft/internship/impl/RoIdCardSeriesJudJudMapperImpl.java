package ro.axonsoft.internship.impl;

import com.esotericsoftware.yamlbeans.YamlReader;
import ro.axonsoft.internship.api.InvalidRoIdCardSeriesException;
import ro.axonsoft.internship.api.Judet;
import ro.axonsoft.internship.api.RoIdCardSeriesJudMapper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class RoIdCardSeriesJudJudMapperImpl implements RoIdCardSeriesJudMapper {
    /**
     * For each series finds a county
     * @param idCardSeries a string with the series
     * @return the corresponding county
     * @throws InvalidRoIdCardSeriesException - if it's not a valid series
     */
    @Override
    public Judet mapIdCardToJud(String idCardSeries) throws InvalidRoIdCardSeriesException {
        try {
            Properties properties = new Properties();
            properties.load(new FileReader("jcis.config"));
            String url = properties.getProperty("ro.axonsoft.internship.jcis.url");
            YamlReader reader = new YamlReader(new FileReader(url));
            Object object = reader.read();
            Map map =(Map)object;

            for (Object key :map.keySet()) {
                List list = (List) map.get(key);
                for (Object obj: list) {
                    if (idCardSeries.toUpperCase(Locale.ROOT).equals(obj.toString())) {
                        return Judet.valueOf(key.toString());
                    }
                }
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        throw new InvalidRoIdCardSeriesException();
    }
}

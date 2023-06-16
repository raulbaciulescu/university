package ro.axonsoft.internship.api;

public interface RoIdCardSeriesJudMapper {
    Judet mapIdCardToJud(String idCardSeries) throws InvalidRoIdCardSeriesException;
}

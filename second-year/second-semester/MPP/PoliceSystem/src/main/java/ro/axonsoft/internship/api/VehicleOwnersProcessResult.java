package ro.axonsoft.internship.api;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * Interfață specificând proprietățile rezultatului procesării proprietarilor de
 * autovehicule.
 */
public interface VehicleOwnersProcessResult extends Serializable {

    /**
     * Raportul dintre numerele de înmatriculare fără soț și cele cu soț*
     * înmuțit cu 100 și rotunjit half-up fără zecimale.
     */
    Integer getOddToEvenRatio();

    /**
     * Numărul de mașini străine deținute de cetățeni românii pe fiecare județ
     * în parte.
     */
    Map<Judet, Integer> getUnregCarsCountByJud();

    /**
     * Numărul de autovehicule deținute de persoane având domiciliul într-un
     * județ și autovehiculul înmatriculat în alt județ și au trecut mai multe
     * de 30 de zile de la data emiterii cărții de indentitate.
     */
    Integer getPassedRegChangeDueDate();

    /**
     * Erorile de procesare.
     */
    Set<VehicleOwnerParseError> getErrors();
}
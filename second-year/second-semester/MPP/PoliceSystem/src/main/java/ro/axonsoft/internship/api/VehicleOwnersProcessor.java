package ro.axonsoft.internship.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.text.ParseException;

/**
 *
 * Procesator de date proprietati de autovehicule.
 *
 */
public interface VehicleOwnersProcessor {

    /**
     * Parsează {@code stream}-ul cu datele de intrare și serializează
     * rezultatul procesării în {@code stream}-ul datelor de ieșire.
     *
     * @param ciCarRegNbInputStream
     *            - stream pentru citire date de intrare
     * @param processResultOutputStream
     *            - stream pentru serializare date de ieșire
     * @throws IOException
     *             dacă apare o eroare în procesare
     */
    void process(InputStream ciCarRegNbInputStream, ObjectOutputStream processResultOutputStream) throws IOException;
}
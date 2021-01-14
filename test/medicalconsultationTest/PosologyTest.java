package medicalconsultationTest;

import exceptions.NullArgumentException;
import medicalconsultation.FqUnit;
import medicalconsultation.Posology;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PosologyTest {

    @Test
    void NullArgumentTest() throws NullArgumentException {
        float dose = Float.parseFloat(null);
        float freq = Float.parseFloat(null);
        FqUnit fqUnit = null;
        try {
            Posology pg = new Posology(dose, freq, fqUnit);
            fail();
        } catch (NullArgumentException e) {

        }
    }
}
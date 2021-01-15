package medicalconsultationTest;

import exceptions.NullArgumentException;
import medicalconsultation.FqUnit;
import medicalconsultation.Posology;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class PosologyTest {

    @Test
    void CorrectPosologyTest() {
        try {
            Posology pg = new Posology(0.5f, 0.1f, FqUnit.HOUR);
        } catch (NullArgumentException ignored) {
            fail();
        }
    }

    @Test
    void NullArgumentTest() {
        try {
            Posology pg = new Posology(0.5f, 0.1f, null);
            fail();
        } catch (NullArgumentException ignored) {

        }
    }
}
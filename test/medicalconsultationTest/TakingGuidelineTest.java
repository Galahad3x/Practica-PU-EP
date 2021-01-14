package medicalconsultationTest;

import exceptions.NullArgumentException;
import medicalconsultation.FqUnit;
import medicalconsultation.TakingGuideline;
import medicalconsultation.dayMoment;
import medicalconsultation.Posology;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class TakingGuidelineTest {
    @Test
    void NullArgumentTest() throws NullArgumentException {
        dayMoment dm = null;
        float du = Float.parseFloat(null);
        String i = null;
        float d = Float.parseFloat(null);
        float f = Float.parseFloat(null);
        FqUnit fqUnit = null;
        try {
            TakingGuideline tgl = new TakingGuideline(dm, du, i, d, f, fqUnit);
            fail();
        } catch (NullArgumentException e) {

        }
    }
}

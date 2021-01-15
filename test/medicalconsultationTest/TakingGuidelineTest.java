package medicalconsultationTest;

import exceptions.NullArgumentException;
import medicalconsultation.FqUnit;
import medicalconsultation.TakingGuideline;
import medicalconsultation.dayMoment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class TakingGuidelineTest {

    @Test
    void CorrectGuidelineTest() {
        try {
            TakingGuideline tgl = new TakingGuideline(dayMoment.AFTERLUNCH, 0.5f, "Pastilla", 1.5f, 75.2f, FqUnit.DAY);
        } catch (NullArgumentException ignored) {
            fail();
        }
    }

    @Test
    void NullArgumentTest() {
        try {
            TakingGuideline tgl = new TakingGuideline(null, 0.5f, "Pastilla", 1.5f, 75.2f, FqUnit.DAY);
            fail();
        } catch (NullArgumentException ignored) {

        }
    }
}

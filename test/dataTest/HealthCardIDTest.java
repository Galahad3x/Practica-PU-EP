package dataTest;
import data.HealthCardID;
import exceptions.NullArgumentException;
import exceptions.WrongFormatException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class HealthCardIDTest {
    @Test
    void HealthCardTest() throws WrongFormatException {
        String personalIDTest = null;
        try {
            HealthCardID hct = new HealthCardID(personalIDTest);
            fail();
        } catch (NullArgumentException e) {

        }
    }

    @Test
    void name() {
    }
}

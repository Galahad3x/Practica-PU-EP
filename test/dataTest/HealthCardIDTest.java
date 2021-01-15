package dataTest;

import data.HealthCardID;
import exceptions.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HealthCardIDTest {
    @Test
    void NullArgumentTest() throws Exception {
        String personalIDTest = null;
        try {
            HealthCardID hct = new HealthCardID(personalIDTest);
            fail();
        } catch (NullArgumentException e) {

        }
    }

    @Test
    void WrongFormatTest() throws Exception{
        String personalIDTest = "BBAR1234567891";
        try {
            HealthCardID hct = new HealthCardID(personalIDTest);
            fail();
        } catch (WrongFormatException e) {

        }
    }

    @Test
    void CorrectFormatTest() throws Exception{
        String personalIDTest = "BBBBBBBBAR123456789123456789";
        try {
            HealthCardID hct = new HealthCardID(personalIDTest);
        } catch (WrongFormatException e) {
            fail();
        }
    }
}

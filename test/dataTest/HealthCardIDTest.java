package dataTest;
import data.HealthCardID;
import exceptions.NullArgumentException;
import exceptions.WrongFormatException;
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
        String personalIDTest = "12345678923473";
        try {
            HealthCardID hct = new HealthCardID(personalIDTest);
            fail();
        } catch (WrongFormatException e) {

        }
    }

    @Test
    void CorrectFormatTest() throws Exception{
        String personalIDTest = "12345678923473";
        try {
            HealthCardID hct = new HealthCardID(personalIDTest);
        } catch (WrongFormatException e) {
            fail();
        }
    }
}

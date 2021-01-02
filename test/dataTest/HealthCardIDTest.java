package dataTest;
import data.HealthCardID;
import exceptions.NullArgumentException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HealthCardIDTest {
    @Test
    void HealthCardTest(){
        String personalIDTest;

        HealthCardID hct = new HealthCardID(personalIDTest);
        assertThrows(hct.getPersonalID(), NullArgumentException);
    }
}

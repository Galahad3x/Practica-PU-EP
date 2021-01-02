package dataTest;

import data.DigitalSignature;
import exceptions.NullArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class DigitalSignatureTest {
    @Test
    void NullArgumentTest() {
        byte[] arr = null;
        try {
            DigitalSignature dig = new DigitalSignature(arr);
            fail();
        } catch (NullArgumentException e) {

        }
    }
}

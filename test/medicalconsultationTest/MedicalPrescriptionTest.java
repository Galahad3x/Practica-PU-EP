package medicalconsultationTest;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import exceptions.IncorrectTakingGuidelinesException;
import exceptions.NullArgumentException;
import exceptions.WrongFormatException;
import medicalconsultation.FqUnit;
import medicalconsultation.MedicalPrescriptionLine;
import medicalconsultation.dayMoment;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import medicalconsultation.MedicalPrescription;

import java.util.Date;
import java.util.HashMap;

class MedicalPrescriptionTest {

    private static final String[] valid_line = {"AFTERBREAKFAST", "2", "Tomar cada dia", "1", "4", "HOUR"};
    private static final String[] invalid_line = {"AFTERBREAKFAST", null, "Tomar cada dia", null, "4", "HOUR"};
    private static final String[] incomplete_line = {"AFTERBREAKFAST", "2", "Tomar cada dia", "1", "4"};

    // Quan ens passen un prodID = null
    @Test
    void NullArgumentTest() throws NullArgumentException, WrongFormatException, IncorrectTakingGuidelinesException {
        ProductID prodID = new ProductID("12345678923473");
        MedicalPrescriptionLine mpl = new MedicalPrescriptionLine(dayMoment.AFTERBREAKFAST, 2,
                "Tomar cada dia", 1, 4, FqUnit.HOUR, prodID);
        MedicalPrescription medp = new MedicalPrescription(3, new Date(), new Date(),
                new HealthCardID("BBBBBBBBAR123456789123456789"), new DigitalSignature(new byte[10]), new HashMap<>());
        try {
            medp.getLines().get(mpl.getPrID());
            medp.addLine(null, valid_line);
            fail();
        } catch (NullArgumentException ignored) {

        }
    }

    // Quan ens passen una linia de prescripció invalida (amb elements null)
    @Test
    void invalidStringTest() throws IncorrectTakingGuidelinesException, NullArgumentException, WrongFormatException {
        ProductID prodID = new ProductID("12345678923473");
        MedicalPrescriptionLine mpl = new MedicalPrescriptionLine(dayMoment.AFTERBREAKFAST, 2,
                "Tomar cada dia", 1, 4, FqUnit.HOUR, prodID);
        MedicalPrescription medp = new MedicalPrescription(3, new Date(), new Date(),
                new HealthCardID("BBBBBBBBAR123456789123456789"), new DigitalSignature(new byte[10]), new HashMap<>());
        try {
            medp.getLines().get(mpl.getPrID());
            medp.addLine(prodID, invalid_line);
            fail();
        } catch (NullArgumentException ignored) {

        }
    }

    // Quan ens passen una linia de prescripció incompleta (amb menys de 6 paràmetres)
    @Test
    void incompleteStringTest() throws WrongFormatException, NullArgumentException {
        ProductID prodID = new ProductID("12345678923473");
        MedicalPrescriptionLine mpl = new MedicalPrescriptionLine(dayMoment.AFTERBREAKFAST, 2,
                "Tomar cada dia", 1, 4, FqUnit.HOUR, prodID);
        MedicalPrescription medp = new MedicalPrescription(3, new Date(), new Date(),
                new HealthCardID("BBBBBBBBAR123456789123456789"), new DigitalSignature(new byte[10]), new HashMap<>());
        try {
            medp.getLines().get(mpl.getPrID());
            medp.addLine(prodID, incomplete_line);
            fail();
        } catch (IncorrectTakingGuidelinesException ignored) {

        }
    }




    @Test
    void correctFormatTest() {

    }

    @Test
    void addLineTest() {

    }

    @Test
    void modifyLineTest() {

    }

    @Test
    void removeLineTest() {

    }
}
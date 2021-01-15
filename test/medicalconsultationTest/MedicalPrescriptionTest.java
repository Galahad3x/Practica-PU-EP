package medicalconsultationTest;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import exceptions.IncorrectTakingGuidelinesException;
import exceptions.NullArgumentException;
import exceptions.ProductNotInPrescriptionException;
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
    private static final String[] incorrectFormat_line = {"bondia", "hola", "Tomar cada dia", "1.2", "4", "quetal"};

    // Quan ens passen un prodID = null
    @Test
    void addLine_NullArgumentTest() throws NullArgumentException, WrongFormatException, IncorrectTakingGuidelinesException {
        ProductID prodID = new ProductID("12345678923473");
        MedicalPrescriptionLine mpl = new MedicalPrescriptionLine(dayMoment.AFTERBREAKFAST, 2,
                "Tomar cada dia", 1, 4, FqUnit.HOUR, prodID);
        MedicalPrescription medp = new MedicalPrescription(3, new Date(), new Date(),
                new HealthCardID("BBBBBBBBAR123456789123456789"), new DigitalSignature(new byte[10]), new HashMap<>());
        try {
            medp.addLine(null, valid_line);
            fail();
        } catch (NullArgumentException ignored) {

        }
    }

    @Test
    void checkAddedLine() throws NullArgumentException, WrongFormatException {
        ProductID prodID = new ProductID("12345678923473");
        MedicalPrescriptionLine mpl = new MedicalPrescriptionLine(dayMoment.AFTERBREAKFAST, 2,
                "Tomar cada dia", 1, 4, FqUnit.HOUR, prodID);
        MedicalPrescription medp = new MedicalPrescription(3, new Date(), new Date(),
                new HealthCardID("BBBBBBBBAR123456789123456789"), new DigitalSignature(new byte[10]), new HashMap<>());
        try {
            medp.addLine(prodID, valid_line);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(medp.getLines().size(), 1);
    }

    // Quan ens passen una linia de prescripció invalida (amb elements null)
    @Test
    void addLine_invalidStringTest() throws IncorrectTakingGuidelinesException, NullArgumentException, WrongFormatException {
        ProductID prodID = new ProductID("12345678923473");
        MedicalPrescriptionLine mpl = new MedicalPrescriptionLine(dayMoment.AFTERBREAKFAST, 2,
                "Tomar cada dia", 1, 4, FqUnit.HOUR, prodID);
        MedicalPrescription medp = new MedicalPrescription(3, new Date(), new Date(),
                new HealthCardID("BBBBBBBBAR123456789123456789"), new DigitalSignature(new byte[10]), new HashMap<>());
        try {
            medp.addLine(prodID, invalid_line);
            fail();
        } catch (NullArgumentException ignored) {

        }
    }

    // Quan ens passen una linia de prescripció incompleta (amb menys de 6 paràmetres)
    @Test
    void addLine_incompleteStringTest() throws WrongFormatException, NullArgumentException {
        ProductID prodID = new ProductID("12345678923473");
        MedicalPrescriptionLine mpl = new MedicalPrescriptionLine(dayMoment.AFTERBREAKFAST, 2,
                "Tomar cada dia", 1, 4, FqUnit.HOUR, prodID);
        MedicalPrescription medp = new MedicalPrescription(3, new Date(), new Date(),
                new HealthCardID("BBBBBBBBAR123456789123456789"), new DigitalSignature(new byte[10]), new HashMap<>());
        try {
            medp.addLine(prodID, incomplete_line);
            fail();
        } catch (IncorrectTakingGuidelinesException ignored) {

        }
    }

    // Ens passen una linia de prescripció amb un format incorrecte
    @Test
    void addLine_incorrectFormatTest() throws NullArgumentException, WrongFormatException {
        ProductID prodID = new ProductID("12345678923473");
        MedicalPrescriptionLine mpl = new MedicalPrescriptionLine(dayMoment.AFTERBREAKFAST, 2,
                "Tomar cada dia", 1, 4, FqUnit.HOUR, prodID);
        MedicalPrescription medp = new MedicalPrescription(3, new Date(), new Date(),
                new HealthCardID("BBBBBBBBAR123456789123456789"), new DigitalSignature(new byte[10]), new HashMap<>());
        try {
            medp.addLine(prodID, incorrectFormat_line);
            fail();
        } catch (IncorrectTakingGuidelinesException ignored) {

        }
    }

    @Test
    void modifyLine_NullProdIDTest() throws NullArgumentException, WrongFormatException,
                                            IncorrectTakingGuidelinesException, ProductNotInPrescriptionException {
        ProductID prodID = new ProductID("12345678923473");
        MedicalPrescriptionLine mpl = new MedicalPrescriptionLine(dayMoment.AFTERBREAKFAST, 2,
                "Tomar cada dia", 1, 4, FqUnit.HOUR, prodID);
        MedicalPrescription medp = new MedicalPrescription(3, new Date(), new Date(),
                new HealthCardID("BBBBBBBBAR123456789123456789"), new DigitalSignature(new byte[10]), new HashMap<>());
        try {
            medp.modifyLine(null, valid_line);
            fail();
        } catch (NullArgumentException ignored) {

        }
    }

    @Test
    void modifyLine_ProdID_NotFoundTest() throws WrongFormatException, NullArgumentException, IncorrectTakingGuidelinesException {
        ProductID prodID = new ProductID("12345678923473");
        // Li passem un ProductID diferent del creat
        MedicalPrescription medp = new MedicalPrescription(3, new Date(), new Date(),
                new HealthCardID("BBBBBBBBAR123456789123456789"), new DigitalSignature(new byte[10]), new HashMap<>());
        try {
            medp.modifyLine(prodID, valid_line);
            fail();
        } catch (ProductNotInPrescriptionException ignored) {

        }
    }



    @Test
    void removeLineTest() {

    }
}
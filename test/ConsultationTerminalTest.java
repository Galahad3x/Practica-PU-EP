import data.*;
import exceptions.*;
import medicalconsultation.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import servicesTest.*;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.fail;

public class ConsultationTerminalTest {

    private static final String[] valid_hcIDs = {"BBBBBBBBQF123456789012345678", "BBBBBBBBQD123456789012345678"};
    // private static final String[] valid_prIDs = {"12345678901234", "12345678901235", "12345678901236", "12345678901237", "12345678901238", "12345678901239"};
    private static final String[] valid_keywords = {"medicament", "pastilles", "crema", "cap", "panxa", "estomac", "dolor", "pell"};
    private static final String[] valid_line = {"AFTERBREAKFAST", "2", "Tomar cada dia", "1", "4", "HOUR"};

    private static final HealthNationalServiceDB hnsDB = new HealthNationalServiceDB();
    private static final ScheduledVisitAgendaDB agendaDB = new ScheduledVisitAgendaDB();

    private static ConsultationTerminal terminal = new ConsultationTerminal();

    @BeforeEach
    void beforeEach() throws NullArgumentException {
        terminal = new ConsultationTerminal();
        int prescCode = 1;
        Date prescDate = new Date();
        Date endDate = new Date();
        HealthCardID hcID = null;
        DigitalSignature sign = null;
        try {
            hcID = new HealthCardID("BBBBBBBBQF123456789012345678");
            sign = new DigitalSignature(new byte[10]);
        } catch (NullArgumentException | WrongFormatException e) {
            e.printStackTrace();
            fail();
        }
        hnsDB.add_prescription(new MedicalPrescription(prescCode, prescDate, endDate, hcID, sign, new HashMap<>()));
        prescCode = 2;
        prescDate = new Date();
        endDate = new Date();
        hcID = null;
        sign = null;
        try {
            hcID = new HealthCardID("BBBBBBBBQD123456789012345678");
            sign = new DigitalSignature(new byte[10]);
        } catch (NullArgumentException | WrongFormatException e) {
            e.printStackTrace();
            fail();
        }
        hnsDB.add_prescription(new MedicalPrescription(prescCode, prescDate, endDate, hcID, sign, new HashMap<>()));

        try {
            hnsDB.add_product(new ProductSpecification(new ProductID("12345678901234"), "medicament cap", BigDecimal.valueOf(3.99)));
            hnsDB.add_product(new ProductSpecification(new ProductID("12345678901235"), "medicament panxa", BigDecimal.valueOf(6.25)));
            hnsDB.add_product(new ProductSpecification(new ProductID("12345678901236"), "pastilles estomac", BigDecimal.valueOf(4.20)));
            hnsDB.add_product(new ProductSpecification(new ProductID("12345678901237"), "pastilles dolor", BigDecimal.valueOf(5)));
            hnsDB.add_product(new ProductSpecification(new ProductID("12345678901238"), "pastilles panxa", BigDecimal.valueOf(9.20)));
            hnsDB.add_product(new ProductSpecification(new ProductID("12345678901239"), "crema pell", BigDecimal.valueOf(17.6)));
        } catch (NullArgumentException | WrongFormatException e) {
            e.printStackTrace();
            fail();
        }

        terminal.seteSignature(new DigitalSignature(new byte[10]));

        for (String hcs : valid_hcIDs) {
            try {
                agendaDB.addVisit(hcs);
            } catch (WrongFormatException | NullArgumentException e) {
                e.printStackTrace();
                fail();
            }
        }

        terminal.setHNS(hnsDB);
        terminal.setAgenda(agendaDB);
    }

    // initRevision();
    void try_init() {
        try {
            terminal.initRevision();
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void initRevisionWithCorrectPatient() {
        try_init();
    }

    // initRevisionEdition();
    void try_initRevision() {
        try {
            //Reiniciar Date per evitar CC
            terminal.getMp().setEndDate(new Date());
            terminal.initPrescriptionEdition();
        } catch (AnyCurrentPrescriptionException e) {
            e.printStackTrace();
            fail();
        } catch (NotFinishedTreatmentException e) {
            System.out.println(terminal.getMp().getEndDate().toString());
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void initRevisionEditionAfterInitRevision() {
        try_init();
        try_initRevision();
    }

    @Test
    void initRevisionEditionWithoutInitRevision() throws NotFinishedTreatmentException {
        try {
            terminal.initPrescriptionEdition();
            fail();
        } catch (AnyCurrentPrescriptionException ignored) {

        }
    }

    @Test
    void initRevisionEditionWithWrongDate() throws AnyCurrentPrescriptionException {
        try_init();
        // Modificar la endDate per a que no sigui vàlida
        terminal.getMp().setEndDate((Date.from(Instant.ofEpochSecond(System.currentTimeMillis() + 100000000))));
        try {
            terminal.initPrescriptionEdition();
            fail();
        } catch (NotFinishedTreatmentException ignored) {

        }
    }

    // searchForProducts(keyword);
    void try_search(String keyword) throws ConnectException {
        try {
            terminal.searchForProducts(keyword);
        } catch (AnyKeyWordMedicineException e) {
            fail();
        }
    }

    @Test
    void searchForProductsCorrect() throws ConnectException {
        try_search(valid_keywords[0]);
    }

    @Test
    void searchForProductsIncorrect() throws ConnectException {
        try {
            terminal.searchForProducts("hola");
            fail();
        } catch (AnyKeyWordMedicineException ignored) {

        }
    }

    // selectProduct(option);
    void try_select(int option) throws ConnectException {
        try {
            terminal.selectProduct(option);
        } catch (AnyMedicineSearchException e) {
            fail();
        }
    }

    @Test
    void selectProductCorrect() throws ConnectException {
        try_search(valid_keywords[1]);
        try_select(0);
    }

    @Test
    void selectProductWithoutPreviousSearch() throws ConnectException {
        try {
            terminal.selectProduct(0);
            fail();
        } catch (AnyMedicineSearchException ignored) {

        }
    }

    @Test
    void selectProductWithOptionTooHigh() throws ConnectException {
        try_search(valid_keywords[2]);
        try {
            terminal.selectProduct(100);
            fail();
        } catch (AnyMedicineSearchException ignored) {

        }
    }

    // enterMedicineGuidelines(String[] instruc)
    void try_enterGuidelines(String[] guidelines) throws AnySelectedMedicineException, NullArgumentException {
        try {
            terminal.enterMedicineGuidelines(guidelines);
        } catch (IncorrectTakingGuidelinesException ignored) {
            // Ignorem la excepció de TakingGuideline
        }
    }

    @Test
    void enterMedicineGuidelinesCorrect() throws ConnectException {
        try_init();
        try_initRevision();
        try_search(valid_keywords[0]);
        try_select(1);
        try {
            try_enterGuidelines(new String[10]);
        } catch (AnySelectedMedicineException | NullArgumentException e) {
            fail();
        }
    }

    @Test
    void enterMedicineGuidelinesWithoutSelectedPrescription() throws ConnectException {
        try_search(valid_keywords[0]);
        try_select(0);
        try {
            try_enterGuidelines(new String[10]);
            fail();
        } catch (NullPointerException ignored) {

        } catch (AnySelectedMedicineException | NullArgumentException e) {
            fail();
        }
    }

    @Test
    void enterMedicineGuidelinesWithoutSelectedProduct() {
        try_init();
        try_initRevision();
        try {
            try_enterGuidelines(new String[10]);
            fail();
        } catch (AnySelectedMedicineException ignored) {

        } catch (NullPointerException | NullArgumentException e) {
            fail();
        }
    }

    @Test
    void enterMedicineGuidelinesWithNullGuidelines() throws ConnectException {
        try_init();
        try_initRevision();
        try_search(valid_keywords[0]);
        try_select(0);
        try {
            try_enterGuidelines(null);
            fail();
        } catch (NullArgumentException ignored) {

        } catch (NullPointerException | AnySelectedMedicineException e) {
            fail();
        }
    }

    //enterTreatmentEndingDate(Date date)
    void try_enterDate(int offset) {
        try {
            terminal.enterTreatmentEndingDate(Date.from(Instant.ofEpochSecond(System.currentTimeMillis() + offset)));
        } catch (IncorrectEndingDateException e) {
            fail();
        }
    }

    @Test
    void enterTreatmentEndingDateCorrect() throws ConnectException {
        try_init();
        try_initRevision();
        try_search(valid_keywords[0]);
        try_select(0);
        try_enterDate(100000000); //Offset positiu: Data del futur
    }

    @Test
    void enterTreatmentEndingDateIncorrect() throws ConnectException {
        try_init();
        try_initRevision();
        try_search(valid_keywords[0]);
        try_select(0);
        try {
            //Offset negatiu: Data del passat
            terminal.enterTreatmentEndingDate(Date.from(Instant.ofEpochSecond(System.currentTimeMillis() - 100000000)));
            fail();
        } catch (IncorrectEndingDateException ignored) {

        }
    }

    //sendePrescription()
    @Test
    void CU_Correcte() throws ConnectException {
        try_init();
        try_initRevision();
        try_search(valid_keywords[0]);
        try_select(0);
        try {
            try_enterGuidelines(valid_line);
        } catch (AnySelectedMedicineException | NullArgumentException e) {
            fail();
        }
        try_enterDate(1000000000);
        try {
            terminal.sendePrescription();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import exceptions.NullArgumentException;
import exceptions.WrongFormatException;
import medicalconsultation.MedicalPrescription;
import medicalconsultation.ProductSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import servicesTest.HealthNationalServiceDB;
import servicesTest.ScheduledVisitAgendaDB;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.fail;

public class ConsultationTerminalTest {

    private static String[] valid_hcIDs = {"BBBBBBBBQF123456789012345678", "BBBBBBBBQD123456789012345678"};
    private static String[] valid_prIDs = {"12345678901234","12345678901235","12345678901236","12345678901237","12345678901238","12345678901239"};
    private static String[] valid_keywords = {"medicament","pastilles","crema","cap","panxa","estomac","dolor","pell"};

    private static HealthNationalServiceDB hnsDB = new HealthNationalServiceDB();
    private static ScheduledVisitAgendaDB agendaDB = new ScheduledVisitAgendaDB();

    private static ConsultationTerminal terminal = new ConsultationTerminal();

    @BeforeAll
    static void beforeEach() {
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
        }

        try {
            hnsDB.setMedic_signature(new DigitalSignature(new byte[10]));
        } catch (NullArgumentException e) {
            e.printStackTrace();
        }

        for (String hcs : valid_hcIDs) {
            try {
                agendaDB.addVisit(hcs);
            } catch (WrongFormatException | NullArgumentException e) {
                e.printStackTrace();
            }
        }

        terminal.setHNS(hnsDB);
        terminal.setAgenda(agendaDB);
    }

    @Test
    void initRevisionWithCorrectPatient() {
        try {
            terminal.initRevision();
        }catch (Exception e){
            fail();
        }
    }
}

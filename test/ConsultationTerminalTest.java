import org.junit.jupiter.api.Test;
import services.HealthNationalService;
import services.ScheduledVisitAgenda;
import servicesTest.HealthNationalServiceDB;
import servicesTest.ScheduledVisitAgendaDB;

public class ConsultationTerminalTest {

    /* HealthCardIDs vàlides:
        BBBBBBBBQF123456789012345678
        BBBBBBBBQD123456789012345678

        ProductIDs vàlides:
        12345678901234
        12345678901235
        12345678901236
        12345678901237
        12345678901238
        12345678901239

        Keywords vàlides:
        medicament
        pastilles
        crema
        cap
        panxa
        estomac
        dolor
        pell
     */

    HealthNationalService hnsDB = new HealthNationalServiceDB();
    ScheduledVisitAgenda agendaDB = new ScheduledVisitAgendaDB();

    @Test
    void prova() {

    }
}

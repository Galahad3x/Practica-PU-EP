import data.DigitalSignature;
import data.HealthCardID;
import exceptions.*;
import medicalconsultation.MedicalPrescription;
import services.HealthNationalService;
import services.ScheduledVisitAgenda;

import java.net.ConnectException;
import java.util.Date;

public class ConsultationTerminal {

    public ScheduledVisitAgenda agenda;
    public HealthNationalService hns;

    public DigitalSignature eSignature;

    private MedicalPrescription mp;

    public void initRevision() throws NullArgumentException, HealthCardException, ConnectException, NotValidePrescriptionException {
        //Obtenir el CIP del pacient per mitjà de l'AVC per descarregar la eRecepta
        HealthCardID patientID = agenda.getHealthCardID();

        //Descarregar la eRecepta per mitjà dl HNS
        setMedicalPrescription(hns.getePrescription(patientID));

        //Mostrar eRecepta en pantalla
        mp.print();
    }

    public void initPrescriptionEdition() throws AnyCurrentPrescriptionException, NotFinishedTreatmentException {

    }

    public void searchForProducts(String keyword) throws AnyMedicineSearchException, ConnectException {

    }

    public void selectProduct(int option) throws AnyMedicineSearchException, ConnectException {

    }

    public void enterMedicineGuidelines(String[] instruc) throws IncorrectTakingGuidelinesException, NullArgumentException, AnySelectedMedicineException {

    }

    public void enterTreatmentEndingDate(Date date) throws IncorrectEndingDateException {

    }

    public void sendePrescription() throws ConnectException, NotValidePrescriptionException, eSignatureException {

    }

    public void getePrescription(){

    }

    public void printePresc() throws PrintingException {
        //Nothing
    }

    public void setMedicalPrescription(MedicalPrescription mp){
        this.mp = mp;
    }
}

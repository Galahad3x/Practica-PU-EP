import data.DigitalSignature;
import data.HealthCardID;
import exceptions.*;
import medicalconsultation.MedicalPrescription;
import medicalconsultation.ProductSpecification;
import services.HealthNationalService;
import services.ScheduledVisitAgenda;

import java.net.ConnectException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class ConsultationTerminal {

    public ScheduledVisitAgenda agenda;
    public HealthNationalService hns;

    public DigitalSignature eSignature;

    private MedicalPrescription mp;

    private List<ProductSpecification> searchResults;
    private ProductSpecification selectedProduct;

    public void initRevision() throws HealthCardException, ConnectException, NotValidePrescriptionException, NullArgumentException {
        //Obtenir el CIP del pacient per mitjà de l'AVC per descarregar la eRecepta
        HealthCardID patientID = agenda.getHealthCardID();

        //Descarregar la eRecepta per mitjà dl HNS
        setMedicalPrescription(hns.getePrescription(patientID));

        if(mp == null){
            throw new NotValidePrescriptionException();
        }

        //Mostrar eRecepta en pantalla
        mp.print();
    }

    public void initPrescriptionEdition() throws AnyCurrentPrescriptionException, NotFinishedTreatmentException {
        if(mp == null){
            throw new AnyCurrentPrescriptionException();
        }

        Date currentEndingDate = mp.getEndDate();
        Date currentDate = Date.from(Instant.ofEpochSecond(System.currentTimeMillis()));

        if(currentDate.before(currentEndingDate)){
            throw new NotFinishedTreatmentException();
        }

    }

    public void searchForProducts(String keyword) throws ConnectException, AnyKeyWordMedicineException {
        searchResults = hns.getProductsByKW(keyword);
    }

    public void selectProduct(int option) throws AnyMedicineSearchException, ConnectException {
        if(searchResults == null || searchResults.size() == 0 || option > searchResults.size()){
            throw new AnyMedicineSearchException();
        }
        selectedProduct = searchResults.get(option);
    }

    public void enterMedicineGuidelines(String[] instruc) throws IncorrectTakingGuidelinesException, NullArgumentException, AnySelectedMedicineException {
        if (selectedProduct == null) {
            throw new AnySelectedMedicineException();
        }
        if (instruc == null){
            throw new NullArgumentException();
        }
        mp.addLine(selectedProduct,instruc);
    }

    public void enterTreatmentEndingDate(Date date) throws IncorrectEndingDateException {
        Date currentDate = Date.from(Instant.ofEpochSecond(System.currentTimeMillis()));
        if(date.before(currentDate)){
            throw new IncorrectEndingDateException();
        }
        mp.setEndDate(date);
    }

    public void sendePrescription() throws ConnectException, NotValidePrescriptionException, eSignatureException, NotCompletedMedicalPrescriptionException {
        hns.sendePrescription(mp);
    }

    public void printePresc() throws PrintingException {
        //Nothing
    }

    public void setHNS(HealthNationalService hns){
        this.hns = hns;
    }

    public void setAgenda(ScheduledVisitAgenda sva){
        this.agenda = sva;
    }

    public void setMedicalPrescription(MedicalPrescription mp) throws NullArgumentException {
        if(mp == null){
            throw new NullArgumentException();
        }
        this.mp = mp;
    }

    public void seteSignature(DigitalSignature ds) throws NullArgumentException {
        if(ds == null){
            throw new NullArgumentException();
        }
        this.eSignature = ds;
    }
}

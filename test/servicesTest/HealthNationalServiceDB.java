package servicesTest;

import data.DigitalSignature;
import data.HealthCardID;
import exceptions.*;
import medicalconsultation.MedicalPrescription;
import medicalconsultation.ProductSpecification;
import services.HealthNationalService;

import java.net.ConnectException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class HealthNationalServiceDB implements HealthNationalService {

    List<MedicalPrescription> prescriptions;
    List<ProductSpecification> products;
    List<ProductSpecification> search_results = null;
    DigitalSignature medic_signature = null;

    public HealthNationalServiceDB() {
        prescriptions = new LinkedList<>();
        products = new LinkedList<>();
    }

    @Override
    public MedicalPrescription getePrescription(HealthCardID hcID) throws HealthCardException, NotValidePrescriptionException, ConnectException {
        for (MedicalPrescription pres : this.prescriptions) {
            if (pres.getHcID().equals(hcID)) {
                return pres;
            }
        }
        // Si no es HealthCardID
        if (!HealthCardID.hasCorrectFormat(hcID.personalID)) {
            throw new HealthCardException();
        }
        // Si una HealthCardID no té associada una eRecepta
        throw new NotValidePrescriptionException();
    }

    @Override
    public List<ProductSpecification> getProductsByKW(String keyword) throws AnyKeyWordMedicineException, ConnectException {
        List<ProductSpecification> resultats = new LinkedList<>();
        for(ProductSpecification prod : this.products){
            if(prod.getDescription().contains(keyword)){
                resultats.add(prod);
            }
        }
        if(resultats.size() == 0){
            throw new AnyKeyWordMedicineException();
        }
        search_results = resultats;
        return resultats;
    }

    @Override
    public ProductSpecification getProductSpecific(int opt) throws AnyMedicineSearchException, ConnectException {
        if (this.search_results == null) {
            throw new AnyMedicineSearchException();
        }
        try {
            return this.search_results.get(opt);
        } catch (IndexOutOfBoundsException e) {
            throw new AnyMedicineSearchException();
        }
    }

    @Override
    public MedicalPrescription sendePrescription(MedicalPrescription ePresc) throws ConnectException, NotValidePrescriptionException, eSignatureException, NotCompletedMedicalPrescriptionException {
        Random rand = new Random();
        int generated_code = rand.nextInt(1000);
        ePresc.setPrescCode(generated_code);
        if(this.medic_signature == null){
            throw new eSignatureException();
        }
        ePresc.seteSign(this.medic_signature);
        if(ePresc.getPrescDate().after(new Date()) || ePresc.getEndDate().before(new Date())){
            throw new NotValidePrescriptionException();
        }
        if(ePresc.getLines().size() == 0){
            throw new NotCompletedMedicalPrescriptionException();
        }
        return ePresc;
    }

    public void add_prescription(MedicalPrescription pres){
        prescriptions.add(pres);
    }

    public void add_product(ProductSpecification prod){
        products.add(prod);
    }

    public void setMedic_signature(DigitalSignature medic_signature) {
        this.medic_signature = medic_signature;
    }
}

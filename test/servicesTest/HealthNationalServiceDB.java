package servicesTest;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import exceptions.*;
import medicalconsultation.MedicalPrescription;
import medicalconsultation.ProductSpecification;
import services.HealthNationalService;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class HealthNationalServiceDB implements HealthNationalService {

    List<MedicalPrescription> prescriptions = new LinkedList<>();
    List<ProductSpecification> products = new LinkedList<>();
    List<ProductSpecification> search_results = null;
    DigitalSignature medic_signature = null;

    public HealthNationalServiceDB() {
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
        prescriptions.add(new MedicalPrescription(prescCode, prescDate, endDate, hcID, sign, new HashMap<>()));
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
        prescriptions.add(new MedicalPrescription(prescCode, prescDate, endDate, hcID, sign, new HashMap<>()));

        try {
            products.add(new ProductSpecification(new ProductID("12345678901234"), "medicament cap", BigDecimal.valueOf(3.99)));
        } catch (NullArgumentException | WrongFormatException e) {
            e.printStackTrace();
        }

        try {
            products.add(new ProductSpecification(new ProductID("12345678901235"), "medicament panxa", BigDecimal.valueOf(6.25)));
        } catch (NullArgumentException | WrongFormatException e) {
            e.printStackTrace();
        }

        try {
            products.add(new ProductSpecification(new ProductID("12345678901236"), "pastilles estomac", BigDecimal.valueOf(4.20)));
        } catch (NullArgumentException | WrongFormatException e) {
            e.printStackTrace();
        }

        try {
            products.add(new ProductSpecification(new ProductID("12345678901237"), "pastilles dolor", BigDecimal.valueOf(5)));
        } catch (NullArgumentException | WrongFormatException e) {
            e.printStackTrace();
        }

        try {
            products.add(new ProductSpecification(new ProductID("12345678901238"), "pastilles panxa", BigDecimal.valueOf(9.20)));
        } catch (NullArgumentException | WrongFormatException e) {
            e.printStackTrace();
        }

        try {
            products.add(new ProductSpecification(new ProductID("12345678901239"), "crema pell", BigDecimal.valueOf(17.6)));
        } catch (NullArgumentException | WrongFormatException e) {
            e.printStackTrace();
        }

        try {
            this.medic_signature = new DigitalSignature(new byte[10]);
        } catch (NullArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MedicalPrescription getePrescription(HealthCardID hcID) throws HealthCardException, NotValidePrescriptionException, ConnectException {
        for (MedicalPrescription pres : this.prescriptions) {
            if (pres.getHcID().equals(hcID)) {
                return pres;
            }
        }
        // Si no es HealthCardID
        if (hcID.getClass() != HealthCardID.class) {
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
}

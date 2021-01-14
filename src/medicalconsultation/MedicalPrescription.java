package medicalconsultation;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import exceptions.IncorrectTakingGuidelinesException;
import exceptions.NullArgumentException;
import exceptions.ProductNotInPrescriptionException;

import java.util.Date;
import java.util.HashMap;

public class MedicalPrescription {

    private int prescCode;
    private Date prescDate;
    private Date endDate;
    private HealthCardID hcID;  // the healthcard ID of the patient
    private DigitalSignature eSign;  // the eSignature of the doctor
    private HashMap<ProductID, MedicalPrescriptionLine> lines;

    public MedicalPrescription(int prescCode, Date prescDate, Date endDate, HealthCardID hcID,
                               DigitalSignature eSign, HashMap<ProductID, MedicalPrescriptionLine> lines) {
        this.prescCode = prescCode;
        this.prescDate = prescDate;
        this.endDate = endDate;
        this.hcID = hcID;
        this.eSign = eSign;
        this.lines = lines;
    }

    // Getters and setters
    public int getPrescCode() {
        return this.prescCode;
    }
    public void setPrescCode(int prescCode) {
        this.prescCode = prescCode;
    }
    public Date getPrescDate() {
        return prescDate;
    }
    public void setPrescDate(Date endDate) {
        this.endDate = endDate;
    }
    public Date getEndDate() {
        return this.endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public DigitalSignature geteSign() {
        return this.eSign;
    }
    public void seteSign(DigitalSignature eSign) {
        this.eSign = eSign;
    }
    public HashMap<ProductID, MedicalPrescriptionLine> getLines() {
        return this.lines;
    }
    public void setLines(HashMap<ProductID, MedicalPrescriptionLine> lines) {
        this.lines = lines;
    }
    public HealthCardID getHcID() {
        return hcID;
    }
    public void setHcID(HealthCardID hcID) {
        this.hcID = hcID;
    }

    public void addLine(ProductID prodID, String[] instruc) throws IncorrectTakingGuidelinesException, NullArgumentException {
        //Parametres nulls
        if (instruc == null || prodID == null) {
            throw new NullArgumentException();
        }
        //Mirem si String[] està incomplet
        if (instruc.length != 6) {
            throw new IncorrectTakingGuidelinesException();
        }
        //Comprovem si hi són tots els elements a l'array d'instruccions
        checkNullElements(instruc);
        checkInstrucArguments(instruc);
        MedicalPrescriptionLine mpl = new MedicalPrescriptionLine(dayMoment.valueOf(instruc[0]), Float.parseFloat(instruc[1]), instruc[2],
                                Float.parseFloat(instruc[3]), Float.parseFloat(instruc[4]), FqUnit.valueOf(instruc[5]));
        lines.put(prodID, mpl);
    }

    public void modifyLine(ProductID prodID, String[] instruc) throws IncorrectTakingGuidelinesException, ProductNotInPrescriptionException, NullArgumentException{
        parseProdID(prodID);
        //Mirem si String[] està incomplet
        if (instruc.length != 6) {
            throw new IncorrectTakingGuidelinesException();
        }

        checkInstrucArguments(instruc);

        MedicalPrescriptionLine mpl = new MedicalPrescriptionLine(dayMoment.valueOf(instruc[0]), Float.parseFloat(instruc[1]), instruc[2],
                Float.parseFloat(instruc[3]), Float.parseFloat(instruc[4]), FqUnit.valueOf(instruc[5]));
        lines.replace(prodID, mpl);
    }

    public void removeLine(ProductID prodID) throws ProductNotInPrescriptionException, NullArgumentException {
        parseProdID(prodID);
        lines.remove(prodID);
    }

    public void checkNullElements(String[] instruc) throws NullArgumentException {
        for (String s : instruc) {
            if (s == null) {
                throw new NullArgumentException();
            }
        }
    }

    public void parseProdID(ProductID prodID) throws NullArgumentException, ProductNotInPrescriptionException {
        if (prodID == null) {
            throw new NullArgumentException();
        }
        /*
         * Comprovem que l'argument prodID estigui a lines per a poder-lo modificar o borrar
         * S'utilitzarà als mètodes "modifyLine" i "removeLine"
         */
        if (!lines.containsKey(prodID)) {
            throw new ProductNotInPrescriptionException();
        }
    }

    public void checkInstrucArguments(String[] instruc) throws IncorrectTakingGuidelinesException {
        try {
            dayMoment.valueOf(instruc[0]);
        } catch (IllegalArgumentException e) {
            throw new IncorrectTakingGuidelinesException();
        }
        try {
            Float.parseFloat(instruc[1]);
        } catch (NumberFormatException e) {
            throw new IncorrectTakingGuidelinesException();
        }
        if (instruc[2] == null) {
            throw new IncorrectTakingGuidelinesException();
        }
        try {
            Float.parseFloat(instruc[3]);
        } catch (NumberFormatException e) {
            throw new IncorrectTakingGuidelinesException();
        }
        try {
            Float.parseFloat(instruc[4]);
        } catch (NumberFormatException e) {
            throw new IncorrectTakingGuidelinesException();
        }
        try {
            FqUnit.valueOf(instruc[5]);
        } catch (IllegalArgumentException e) {
            throw new IncorrectTakingGuidelinesException();
        }
    }


    public void print() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "MedicalPrescription{" +
                "prescCode=" + prescCode +
                ", prescDate=" + prescDate +
                ", endDate=" + endDate +
                ", hcID=" + hcID +
                ", eSign=" + eSign +
                ", lines=" + lines +
                '}';
    }
}

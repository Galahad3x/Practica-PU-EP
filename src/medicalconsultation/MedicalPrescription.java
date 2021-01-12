package medicalconsultation;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import exceptions.IncorrectTakingGuidelinesException;
import exceptions.NullArgumentException;
import exceptions.ProductNotInPrescriptionException;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.List;

public class MedicalPrescription {

    private int prescCode;
    private Date prescDate;
    private Date endDate;
    private HealthCardID hcID;  // the healthcard ID of the patient
    private DigitalSignature eSign;  // the eSignature of the doctor
    private List<MedicalPrescriptionLine> lines;

    public MedicalPrescription(int prescCode, Date prescDate, Date endDate, HealthCardID hcID,
                               DigitalSignature eSign, List<MedicalPrescriptionLine> lines) {
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

    public List<MedicalPrescriptionLine> getLines() {
        return this.lines;
    }

    public void setLines(List<MedicalPrescriptionLine> lines) {
        this.lines = lines;
    }

    public void addLine(ProductID prodID, String[] instruc) throws IncorrectTakingGuidelinesException, NullArgumentException {
        if (instruc == null || prodID == null) {
            throw new NullArgumentException();
        }

        try {
            dayMoment dM = dayMoment.valueOf(instruc[0]);
        } catch (IllegalArgumentException e) {
            throw new IncorrectTakingGuidelinesException();
        }

        try {
            float duration = Float.parseFloat(instruc[1]);
        } catch (NumberFormatException e) {
            throw new IncorrectTakingGuidelinesException();
        }

        /*
        try {
            instructions = instruc[2];
        } catch (NullArgumentException e) {
            throw new NullArgumentException();
        }
        */

        try {
            float dose = Float.parseFloat(instruc[3]);
        } catch (NumberFormatException e) {
            throw new IncorrectTakingGuidelinesException();
        }

        try {
            float freq = Float.parseFloat(instruc[4]);
        } catch (NumberFormatException e) {
            throw new IncorrectTakingGuidelinesException();
        }

        try {
            FqUnit fqUnit = FqUnit.valueOf(instruc[5]);
        } catch (IllegalArgumentException e) {
            throw new IncorrectTakingGuidelinesException();
        }

        TakingGuideline tg = new TakingGuideline(dayMoment.valueOf(instruc[0]), Float.parseFloat(instruc[1]), instruc[2], Float.parseFloat(instruc[3]), Float.parseFloat(instruc[4]), FqUnit.valueOf(instruc[5]));

    }

    public void modifyLine(ProductID prodID, String[] instruc) throws IncorrectTakingGuidelinesException, ProductNotInPrescriptionException {

    }

    public void removeLine(ProductID prodID) throws ProductNotInPrescriptionException{

    }

    public void print(){
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

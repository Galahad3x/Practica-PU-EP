package medicalconsultation;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import exceptions.IncorrectTakingGuidelinesException;
import exceptions.NullArgumentException;
import exceptions.ProductNotInPrescriptionException;

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
        if (instruc == null) {
            throw new NullArgumentException();
        }
        for (int i = 0; i < instruc.length; i++) {
            if (!instruc[i].equals(hcID.getPersonalID())) {
                throw new IncorrectTakingGuidelinesException();
            }
        }
    }

    public void modifyLine(ProductID prodID, String[] instruc) throws IncorrectTakingGuidelinesException, ProductNotInPrescriptionException {

    }

    public void removeLine(ProductID prodID) throws ProductNotInPrescriptionException{

    }

}

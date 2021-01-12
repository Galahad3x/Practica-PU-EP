package medicalconsultation;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import exceptions.IncorrectTakingGuidelinesException;
import exceptions.ProductNotInPrescriptionException;

import java.util.Date;
import java.util.List;

public class MedicalPrescription {

    private int prescCode;
    private Date prescDate;
    private Date endDate;
    private HealthCardID hcID;
    private DigitalSignature eSign;
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

    public void addLine(ProductID prodID, String[] instruc) throws IncorrectTakingGuidelinesException {

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

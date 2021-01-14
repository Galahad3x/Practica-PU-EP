package medicalconsultation;

import data.ProductID;
import exceptions.NullArgumentException;

public class MedicalPrescriptionLine {
    private TakingGuideline guideline;
    private ProductID prID;

    public MedicalPrescriptionLine(dayMoment dayMoment, float duration, String instructions, float dose, float freq, FqUnit fqUnit, ProductID prID) throws NullArgumentException {
        this.guideline = new TakingGuideline(dayMoment, duration, instructions, dose, freq, fqUnit);
        this.prID = prID;
    }

    public void setGuideline(TakingGuideline guideline) {
        this.guideline = guideline;
    }

    public TakingGuideline getGuideline() {
        return guideline;
    }

    public void setPrID(ProductID prID) {
        this.prID = prID;
    }

    public ProductID getPrID() {
        return prID;
    }
}

package medicalconsultation;

import data.ProductID;
import exceptions.NullArgumentException;

public class MedicalPrescriptionLine {
    private TakingGuideline guideline;
    private ProductID prodID;

    public MedicalPrescriptionLine(dayMoment dayMoment, float duration, String instructions, float dose, float freq, FqUnit fqUnit, ProductID prodID) throws NullArgumentException {
        this.guideline = new TakingGuideline(dayMoment, duration, instructions, dose, freq, fqUnit);
        this.prodID = prodID;
    }

    public void setGuideline(TakingGuideline guideline) {
        this.guideline = guideline;
    }

    public TakingGuideline getGuideline() {
        return guideline;
    }

    public void setPrID(ProductID prodID) {
        this.prodID = prodID;
    }

    public ProductID getPrID() {
        return prodID;
    }
}

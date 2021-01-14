package medicalconsultation;

import exceptions.NullArgumentException;

public class MedicalPrescriptionLine {
    private TakingGuideline guideline;
    private ProductSpecification prSpec;

    public MedicalPrescriptionLine(dayMoment dayMoment, float duration, String instructions, float dose, float freq, FqUnit fqUnit, ProductSpecification prSpec) throws NullArgumentException {
        this.guideline = new TakingGuideline(dayMoment, duration, instructions, dose, freq, fqUnit);
        this.prSpec = prSpec;
    }

    public void setGuideline(TakingGuideline guideline) {
        this.guideline = guideline;
    }

    public TakingGuideline getGuideline() {
        return guideline;
    }

    public void setPrID(ProductSpecification prSpec) {
        this.prSpec = prSpec;
    }

    public ProductSpecification getPrID() {
        return prSpec;
    }
}

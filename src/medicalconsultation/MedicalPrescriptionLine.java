package medicalconsultation;

import exceptions.NullArgumentException;

public class MedicalPrescriptionLine {
    private TakingGuideline guideline;

    public MedicalPrescriptionLine(dayMoment dayMoment, float duration, String instructions, float dose, float freq, FqUnit fqUnit) throws NullArgumentException {
        this.guideline = new TakingGuideline(dayMoment, duration, instructions, dose, freq, fqUnit);
    }

    public void setGuideline(TakingGuideline guideline) {
        this.guideline = guideline;
    }

    public TakingGuideline getGuideline() {
        return guideline;
    }
}

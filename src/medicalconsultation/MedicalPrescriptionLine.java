package medicalconsultation;

public class MedicalPrescriptionLine {
    private dayMoment dayMoment;
    private float duration;
    private String instructions;
    private float dose;
    private float freq;
    private FqUnit fqUnit;

    public MedicalPrescriptionLine(dayMoment dayMoment, float duration, String instructions, float dose, float freq, FqUnit fqUnit){
        this.dayMoment = dayMoment;
        this.duration = duration;
        this.instructions = instructions;
        this.dose = dose;
        this.freq = freq;
        this.fqUnit = fqUnit;
    }
}

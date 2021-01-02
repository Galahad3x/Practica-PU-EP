package medicalconsultation;

import exceptions.*;

public class TakingGuideline {
    private dayMoment dMoment;

    private float duration;

    private String instructions;

    private Posology posology;

    public TakingGuideline(dayMoment dM, float du, String i, float d, float f, FqUnit u) throws NullArgumentException {
        this.posology = new Posology(d, f, u);
        if ( dM == null || i == null ){
            throw new NullArgumentException();
        }
        this.dMoment = dM;
        this.duration = du;
        this.instructions = i;
    }
}

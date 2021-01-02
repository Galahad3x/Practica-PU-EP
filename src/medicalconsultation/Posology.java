package medicalconsultation;

import exceptions.*;

public class Posology {
    private float dose;

    private float freq;

    private FqUnit freqUnit;

    public Posology(float d, float f, FqUnit u) throws NullArgumentException{
        if( u == null ){
            throw new NullArgumentException();
        }
        this.dose = d;
        this.freq = f;
        this.freqUnit = u;
    }
}

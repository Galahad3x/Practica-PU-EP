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

    public float getDose() {
        return dose;
    }

    public void setDose(){
        this.dose = dose;
    }

    public float getFreq(){
        return freq;
    }

    public void setFreq(){
        this.freq = freq;
    }

    public FqUnit getFreqUnit(){
        return freqUnit;
    }

    public void setFreqUnit(){
        this.freqUnit = freqUnit;
    }
}

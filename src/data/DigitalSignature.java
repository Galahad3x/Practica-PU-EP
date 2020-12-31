package data;

import java.util.Arrays;

/***
 * The personal identifying code in the National Health Service.
 */

final public class DigitalSignature {

    private final byte[] signature;

    public DigitalSignature(byte[] signature) {
        this.signature = signature;
    }

    public byte[] getSignature() {
        return this.signature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DigitalSignature hcardID= (DigitalSignature) o;
        return Arrays.equals(signature, hcardID.signature);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(signature);
    }

    @Override
    public String toString() {
        return "DigitalSignature{" + "signature='" + Arrays.toString(signature) + '\'' +'}';
    }
}
package data;

import exceptions.*;

/***
 * The personal identifying code in the National Health Service.
 */

final public class HealthCardID {

    private final String personalID;

    public HealthCardID(String code) throws NullArgumentException, WrongFormatException {
        if (code == null){
            throw new NullArgumentException();
        }
        if (!hasCorrectFormat(code)){
            throw new WrongFormatException();
        }
        this.personalID = code;
    }

    public static boolean hasCorrectFormat(String code){
        return code.matches("B{8}[A-Z][A-Z][0-9]{6}[0-9]{12}");
    }

    public String getPersonalID() {
        return personalID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HealthCardID hcardID= (HealthCardID) o;
        return personalID.equals(hcardID.personalID);
    }

    @Override
    public int hashCode() {
        return personalID.hashCode();
    }

    @Override
    public String toString() {
        return "HealthCardID{" + "personal code='" + personalID + '\'' +'}';
    }
}
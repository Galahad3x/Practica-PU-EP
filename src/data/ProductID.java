package data;

import exceptions.*;

/***
 * The personal identifying code of a product.
 */

final public class ProductID {

    private final String productID;

    public ProductID(String code) throws NullArgumentException, WrongFormatException {
        if (code == null){
            throw new NullArgumentException();
        }
        if (!hasCorrectFormat(code)){
            throw new WrongFormatException();
        }
        this.productID = code;
    }

    public static boolean hasCorrectFormat(String code){
        //Número de 14 dígits
        return code.matches("[0-9]{14}");
    }

    public String getProductID() {
        return productID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductID hcardID= (ProductID) o;
        return productID.equals(hcardID.productID);
    }

    @Override
    public int hashCode() {
        return productID.hashCode();
    }

    @Override
    public String toString() {
        return "ProductID{" + "product code='" + productID + '\'' +'}';
    }
}
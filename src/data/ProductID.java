package data;

/***
 * The personal identifying code in the National Health Service.
 */

final public class ProductID {

    private final String productID;

    public ProductID(String code) {
        this.productID = code;
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
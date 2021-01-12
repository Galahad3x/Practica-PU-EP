package medicalconsultation;

import data.ProductID;
import exceptions.NullArgumentException;

import java.math.BigDecimal;

public class ProductSpecification {

    private ProductID prodID;
    private String description;
    private BigDecimal price;

    public ProductSpecification(ProductID prodID, String description, BigDecimal price) throws NullArgumentException {
        if (prodID == null || description == null || price == null) {
            throw new NullArgumentException();
        }
        this.prodID = prodID;
        this.description = description;
        this.price = price;
    }

    public void setProdID(ProductID prodID) throws NullArgumentException {
        if (prodID == null){
            throw new NullArgumentException();
        }
        this.prodID = prodID;
    }

    public void setDescription(String description) throws NullArgumentException {
        if (description == null){
            throw new NullArgumentException();
        }
        this.description = description;
    }

    public void setPrice(BigDecimal price) throws NullArgumentException {
        if (price == null){
            throw new NullArgumentException();
        }
        this.price = price;
    }

    public ProductID getProdID() {
        return prodID;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }
}

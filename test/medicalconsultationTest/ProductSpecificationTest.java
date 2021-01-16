package medicalconsultationTest;

import data.ProductID;
import medicalconsultation.ProductSpecification;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.fail;

public class ProductSpecificationTest {

    @Test
    void CorrectArgumentsTest() {
        try {
            ProductID prodID = new ProductID("12345678901234");
            String description = "Medicina per al mal de cap";
            BigDecimal price = BigDecimal.valueOf(3.99);
            ProductSpecification prodS = new ProductSpecification(prodID, description, price);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void NullArgumentsTest() {
        try {
            ProductID prodID = new ProductID("12345678901234");
            String description = null;
            BigDecimal price = BigDecimal.valueOf(3.99);
            ProductSpecification prodS = new ProductSpecification(prodID, description, price);
            fail();
        } catch (Exception e) {
            // Do nothing
        }
    }

    @Test
    void settersTest() {
        try {
            ProductID prodID = new ProductID("12345678901234");
            String description = "Pastilles";
            BigDecimal price = BigDecimal.valueOf(3.99);
            ProductSpecification prodS = new ProductSpecification(prodID, description, price);
            prodS.setProdID(prodID);
            prodS.setDescription("Medicament");
            prodS.setPrice(BigDecimal.valueOf(5.99));
        } catch (Exception e) {
            fail();
        }
    }
}

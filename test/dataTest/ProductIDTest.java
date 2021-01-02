package dataTest;

import data.*;
import exceptions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class ProductIDTest {

    @Test
    void NullArgumentTest() throws Exception{
        String code = null;
        try{
            ProductID prod = new ProductID(code);
            fail();
        }catch (NullArgumentException e){

        }
    }

    @Test
    void WrongFormatTest() throws Exception {
        String code = "12345678923473";
        try{
            ProductID prod = new ProductID(code);
            fail();
        }catch (NullArgumentException e){

        }
    }

    @Test
    void CorrectFormatTest() throws Exception {
        String code = "12345678923473";
        try{
            ProductID prod = new ProductID(code);
        }catch (NullArgumentException e){
            fail();
        }
    }
}

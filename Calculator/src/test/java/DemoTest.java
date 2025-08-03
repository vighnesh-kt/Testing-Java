import com.v.Calculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Test Math Operation in calculator class")
public class DemoTest {


    @Test
    @DisplayName("Test Division by 0")
    public void testIntegerDivision_WhenDividedBy0_shouldThrowArithmeticException(){
        int divident=4;
        int divisor=0;
        Calculator c=new Calculator();

        assertThrows(ArithmeticException.class,()->{
            c.integerDivision(divident,divisor);
        });

    }

    @Test
    @DisplayName("Division 4/2 = 2")
    public void testIntegerDivision(){

        System.out.println("Hello");
        Calculator c=new Calculator();



        int res = c.integerDivision(4, 2);
//        assertEquals(2, res, "Values should be equal");
//        assertNotEquals(4,res,"Not equal to 2");
//        assertNotNull(c);

//        assertThrows(Throwable.class,()->{
//            System.out.println("Thrown");
//            int i = 0 / 0;
//        });


    }

    @Test
    @DisplayName("Subtraction 10-5 = 5")
    public void integerSubtraction(){
        Calculator c= new Calculator();
        int minusend=10;
        int subtractend=5;
        int expectedResult=5;
        int result = c.integerSubtraction(10, 4);

           assertEquals(expectedResult,result,()->minusend+" "+subtractend+" did not produce expected "+result);


    }

}

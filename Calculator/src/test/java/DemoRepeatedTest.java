import com.v.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemoRepeatedTest {

    static Calculator c;
    @BeforeEach
    public void beforeEach(){
        System.out.println("Executing before each method");
        c= new Calculator();
    }


    @RepeatedTest(3)
//    @ParameterizedTest
    @MethodSource
    public void integerSubtraction(RepetitionInfo test){

        int minuend=4;
        int subtrahend=2;
        int expectedResult=2;


        int result = c.integerSubtraction(minuend, subtrahend);

        System.out.println(test.getCurrentRepetition()+" "+minuend +" - "+subtrahend+" = "+expectedResult);

        assertEquals(expectedResult,result,()->minuend+" "+subtrahend+" did not produce expected "+result);


    }



}

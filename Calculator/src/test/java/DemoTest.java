import com.v.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Test Math Operation in calculator class")
public class DemoTest {

    Calculator c;

    @BeforeAll
    public static void setup(){
        System.out.println("Executing Before All method");
    }

    @AfterAll
    public static void cleanup(){
        System.out.println("Executing After All method");
    }

    @BeforeEach
    public void beforeEach(){
        System.out.println("Executing before each method");
        c= new Calculator();
    }

    @AfterEach
    public void afterEach(){
        System.out.println("Executing after each method");
    }




    //method naming convention
    //test<System under test>_<condition or state change>_<expected result>
    @Test
    @DisplayName("Test Division by 0")
    public void testIntegerDivision_WhenDividedBy0_shouldThrowArithmeticException(){

        //AAA

        //Arrange
        int dividend=4;
        int divisor=0;
        String expectedException="/ by zero";


        //Act and assert
        ArithmeticException arithmeticException = assertThrows(ArithmeticException.class, () -> {
            //act

            //act
            c.integerDivision(dividend, divisor);
        }, "Division by 0 showld have thrown Arithmetic exception");

        //assert
        assertEquals(expectedException,arithmeticException.getMessage(),"Unexpected exception occur");
    }

    @Test
    @Disabled("Task needed ")
    @DisplayName("Division 4/2 = 2")
    public void test_IntegerDivision_WhenFourDividedByTwo_ShouldReturnTwo(){

        //Arrange  another name given
        int dividend=4;
        int divisor=2;
        int expectedResult=2;

        //act  //when
        int res = c.integerDivision(4, 2);

        assertEquals(expectedResult,res,()->dividend+" "+divisor+" did not produce expected "+res);


        //assert  //then

//        assertEquals(2, res, "Values should be equal");
//        assertNotEquals(4,res,"Not equal to 2");
//        assertNotNull(c);

//        assertThrows(Throwable.class,()->{
//            System.out.println("Thrown");
//            int i = 0 / 0;
//        });


    }


    @ParameterizedTest
//    @ValueSource(strings = {"Bond","Wayne",})
    @NullSource
    void valueSoruceDemonstration(String firstName){

        System.out.println(firstName);
        assertNotNull(firstName);
    }

    @ParameterizedTest
//    @MethodSource()
//    @CsvFileSource(resources = "/integerSubtraction.csv")
//    @CsvSource({
//           "1,2,-1",
//            "15,5,10"
//
//    })
    @DisplayName("Test Integer Subtraction [minuend,subtrahend,expectedResult")
    public void integerSubtraction(int minuend,int subtrahend,int expectedResult){

        int result = c.integerSubtraction(minuend, subtrahend);

        System.out.println(minuend +" - "+subtrahend+" = "+expectedResult);

           assertEquals(expectedResult,result,()->minuend+" "+subtrahend+" did not produce expected "+result);


    }

    static Stream<Arguments> integerSubtraction(){

        return Stream.of(
                Arguments.of(10,5,5),
                Arguments.of(5,5,0),
                Arguments.of(15,5,10)
        );
    }

}

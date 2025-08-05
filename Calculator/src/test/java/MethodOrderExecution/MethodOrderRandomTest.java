package MethodOrderExecution;

import org.junit.jupiter.api.Test;


//@TestMethodOrder(MethodOrderer.Random.class)
public class MethodOrderRandomTest {

    @Test
    public void testA(){
        System.out.println("TestA");
    }

    @Test
    public void testB(){
        System.out.println("TestB");
    }
    @Test
    public void testC(){
        System.out.println("TestC");
    }

    @Test
    public void testD(){
        System.out.println("TestD");
    }
}

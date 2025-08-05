package MethodOrderExecution;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;


@TestMethodOrder(MethodOrderer.MethodName.class)
public class MethodOrderNameTest {

    @Test
    public void testC(){
        System.out.println("TestC");
    }
    @Test
    public void testA(){
        System.out.println("TestA");
    }

    @Test
    public void testB(){
        System.out.println("TestB");
    }

    @Test
    public void testD(){
        System.out.println("TestD");
    }
}

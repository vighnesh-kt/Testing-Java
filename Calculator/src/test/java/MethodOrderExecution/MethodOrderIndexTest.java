package MethodOrderExecution;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MethodOrderIndexTest {

    @Test
    public void testA(){
        System.out.println("TestA");
    }

    @Test
    public void testB(){
        System.out.println("TestB");
    }
    @Test
    @Order(2)
    public void testC(){
        System.out.println("TestC");
    }

    @Test
    @Order(1)
    public void testD(){
        System.out.println("TestD");
    }
}

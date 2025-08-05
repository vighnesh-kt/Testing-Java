package MethodOrderExecution;

import org.junit.jupiter.api.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MethodOrderIndexTest {

    @AfterAll
            public void printBuilder(){
        System.out.println(b);
    }

    StringBuilder b=new StringBuilder();

    @Test
    public void testA(){
        b.append(1);
        System.out.println("TestA");
    }

    @Test
    public void testB(){
        b.append(2);
        System.out.println("TestB");
    }
    @Test
    @Order(2)
    public void testC(){
        b.append(3);
        System.out.println("TestC");
    }

    @Test
    @Order(1)
    public void testD(){
        b.append(4);
        System.out.println("TestD");
    }
}

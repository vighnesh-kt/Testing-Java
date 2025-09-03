package com.v.testingrest.ui.controller;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class UsersControllerWithTestContainerTest {

    //static so to make single instance and share across all members as by default new instance creeated for each test method
    //gettting image from docker and adding @Container
    @Container
    private static MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest")
            .withDatabaseName("photo_app")
            .withUsername("admin")
            .withPassword("admin");


    @DynamicPropertySource
    private static void overrideProperties(DynamicPropertyRegistry dynamicPropertyRegistry){

//        dynamicPropertyRegistry.add("spring.datasource.url",mySQLContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.url",()->mySQLContainer.getJdbcUrl());
    }


}

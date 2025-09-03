package com.v.testingrest.ui.controller;


import com.v.testingrest.security.SecurityConstants;
import com.v.testingrest.ui.response.UserRest;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsersControllerWithTestContainerTest {

    //static so to make single instance and share across all members as by default new instance creeated for each test method
    //gettting image from docker and adding @Container
    @Container
    @ServiceConnection
    private static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.4.0")
            .withUsername("admin")
            .withPassword("admin");

    static{
        mySQLContainer.start();
    }

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String authorizationToken;


    //overriding jdbc url values which we have in application.prop file at runtime ie the container prop will be loaded for springbootapp
    // dynamically
//    @DynamicPropertySource
//    private static void overrideProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
//
//        dynamicPropertyRegistry.add("spring.datasource.url",mySQLContainer::getJdbcUrl);
//        dynamicPropertyRegistry.add("spring.datasource.username",mySQLContainer::getUsername);
//        dynamicPropertyRegistry.add("spring.datasource.password",mySQLContainer::getPassword);
//    }


    // Validating if container is created and running
    @Test
    @DisplayName("Test container is created and running")
    @Order(1)
     void testContainerIsRunning(){
        Assertions.assertTrue(mySQLContainer.isCreated(),"MySql container has not been created");
        Assertions.assertTrue(mySQLContainer.isRunning(),"MySql container is not Running");
    }

    @Test
    @DisplayName("User can be created")
    @Order(2)
    void testCreateUser_whenValidDetailsProvided_createUser() throws JSONException {

        //Arrange
        JSONObject userDetailsRequestJSON = new JSONObject();
        userDetailsRequestJSON.put("firstName","Bruce");
        userDetailsRequestJSON.put("lastName","Wayne");
        userDetailsRequestJSON.put("email","im@Batman");
        userDetailsRequestJSON.put("password","whysoserious");
        userDetailsRequestJSON.put("repeatPassword","whysoserious");



        //Creating Headers
        HttpHeaders headers =new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));//List of headers

        //Creating Request
        HttpEntity<String> request=new HttpEntity<>(userDetailsRequestJSON.toString(),headers);


        //Sending request using rest template


        //Act
        ResponseEntity<UserRest> userRestResponseEntity = testRestTemplate.postForEntity("/users/create",
                request,
                UserRest.class);

        UserRest userRest=userRestResponseEntity.getBody();

        //Assert

        Assertions.assertEquals(HttpStatus.OK,userRestResponseEntity.getStatusCode(),"Status code doesnt match");

        Assertions.assertEquals(userDetailsRequestJSON.get("firstName"),userRest.getFirstName(),"Returned User first name is incorrect");


    }

    @Test
    @DisplayName("Get /users required JWT")
    @Order(3)
    void testCreateUse_whenMissingJWT_return403(){

        //Arrange
        //Creating Headers
        HttpHeaders headers =new HttpHeaders();
        headers.set("Accept","application/json");

        HttpEntity requestEntity =new HttpEntity(null,headers);

        //Act
        ResponseEntity<UserRest> exchange = testRestTemplate.exchange("/users",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<UserRest>() {
                });

        //Assert
        Assertions.assertEquals(HttpStatus.FORBIDDEN,exchange.getStatusCode(),"Status code doesnt match");

    }

    @Test
    @DisplayName("login works")
    @Order(4)
    void testUserLogin_whenValidUserCredentialsProvided_returnsJWTinAuthHeader() throws JSONException {

        // Arrange

        //Login Credentials

//        String loginCredentialsJson = "{\n" +
//                "    \"email\":\"test3@test.com\",\n" +
//                "    \"password\":\"12345678\"\n" +
//                "}";
        JSONObject loginCredentials = new JSONObject();
        loginCredentials.put("email","im@Batman");
        loginCredentials.put("password","whysoserious");

        HttpEntity<String> request = new HttpEntity<>(loginCredentials.toString());


        // Act
        ResponseEntity response = testRestTemplate.postForEntity("/users/login",
                request,
                null); //as we are not receiving anything

        authorizationToken = response.getHeaders().
                getValuesAsList(SecurityConstants.HEADER_STRING).get(0);

        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(),
                "HTTP Status code should be 200");
        Assertions.assertNotNull(authorizationToken,
                "Response should contain Authorization header with JWT");
        Assertions.assertNotNull(response.getHeaders().
                        getValuesAsList("UserID").get(0),
                "Response should contain UserID in a response header");



    }

    @Test
    @Order(5)
    @DisplayName("GET /users works")
    void testGetUsers_whenValidJWTProvided_returnsUsers() {
        // Arrange
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(authorizationToken); //this will a token to header so that we can validate

        HttpEntity requestEntity = new HttpEntity(headers);

        // Act
        ResponseEntity<List<UserRest>> response = testRestTemplate.exchange("/users",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<UserRest>>() {
                });

        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(),
                "HTTP Status code should be 200");
        Assertions.assertTrue(response.getBody().size() == 1,
                "There should be exactly 1 user in the list");
    }




}

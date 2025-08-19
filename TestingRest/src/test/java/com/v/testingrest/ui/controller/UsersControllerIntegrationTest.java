package com.v.testingrest.ui.controller;

import com.v.testingrest.ui.response.UserRest;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.Arrays;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UsersControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("User can be created")
    @Order(1)
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
        HttpEntity<String>request=new HttpEntity<>(userDetailsRequestJSON.toString(),headers);


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
    @Order(2)
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
    @Order(3)
    void testUserLogin_validUserCredentialsProvided_returnsJWTinAuthHeader() throws JSONException {

        // Arrange

        //Login Credentials

//        String loginCredentialsJson = "{\n" +
//                "    \"email\":\"test3@test.com\",\n" +
//                "    \"password\":\"12345678\"\n" +
//                "}";
        JSONObject loginCredentials = new JSONObject();
        loginCredentials.put("email","test@test.com");
        loginCredentials.put("password","12345678");

        HttpEntity<String> request = new HttpEntity<>(loginCredentials.toString());


        // Act
        ResponseEntity response = testRestTemplate.postForEntity("/users/login",
                request,
                null); //as we are not receiving anything

//        authorizationToken = response.getHeaders().
//                getValuesAsList(SecurityConstants.HEADER_STRING).get(0);

        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(),
                "HTTP Status code should be 200");
//        Assertions.assertNotNull(authorizationToken,
//                "Response should contain Authorization header with JWT");
        Assertions.assertNotNull(response.getHeaders().
                        getValuesAsList("UserID").get(0),
                "Response should contain UserID in a response header");



    }


}

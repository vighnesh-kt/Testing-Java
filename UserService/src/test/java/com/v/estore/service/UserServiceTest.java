package com.v.estore.service;

import com.v.estore.data.UsersRepository;
import com.v.estore.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    UserService userService;
    String username ;
    String lastName;
    String password;

    @BeforeEach
    void init(){
         userService = new UserServiceImpl();

         username = "Bruce";
         lastName = "Wayne";
         password="Im@batman";
    }

    @Test
    void testCreateUser_whenUserDetailsAreProvided_returnUserObject() {

        //Arrange


        //Act
        User user = userService.createUser(username, lastName, password);
        System.out.println(user.toString());

        //Assert
        assertNotNull(user,"Got null object here");

    }
}

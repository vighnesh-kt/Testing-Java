package com.v.estore.service;

import com.v.estore.data.UsersRepository;
import com.v.estore.exception.EmailVerificationServiceException;
import com.v.estore.exception.UserServiceException;
import com.v.estore.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UsersRepository usersRepository;

    @Mock
    EmailVerificationServiceImpl emailVerificationService;

    String username ;
    String lastName;
    String password;

    @BeforeEach
    void init(){
         username = "Bruce";
         lastName = "Wayne";
         password="Im@batman";
    }

    @Test
    @DisplayName("User Creation Test")
    void testCreateUser_whenUserDetailsAreProvided_returnUserObject() {

        //Arrange
        Mockito.when(usersRepository.saveUser(Mockito.any(User.class))).thenReturn(true);

        //Act
        User user = userService.createUser(username, lastName, password);
        System.out.println(user.toString());

        //Assert
        assertNotNull(user,"Got null object here");
        Mockito.verify(usersRepository,Mockito.times(2)).saveUser(Mockito.any(User.class));

    }

    @Test
    void testCreateUser_whenSaveMethodThrowsException_thenThrowsUserServiceException(){
        //Arrange
        Mockito.when(usersRepository.saveUser(Mockito.any(User.class))).thenThrow(RuntimeException.class);
        //Act and assert
        assertThrows(UserServiceException.class,()->{
            User user = userService.createUser(username, lastName, password);
        },"Should have thrown user service exception");
        //Assert

    }

    @Test
    @DisplayName("Email notificationexception")
    void testCreateUser_whenEmailNotificationThrown_throwUserServiceException(){

        //Arrange
        Mockito.when(usersRepository.saveUser(Mockito.any(User.class))).thenReturn(true);
//      Mockito.when(emailVerificationService.scheduleEmailConfirmation(Mockito.any(User.class)));

        Mockito.doThrow(EmailVerificationServiceException.class).when(emailVerificationService).scheduleEmailConfirmation(Mockito.any(User.class));

        Mockito.doNothing().when(emailVerificationService).scheduleEmailConfirmation(Mockito.any(User.class));
        //act
        assertThrows(UserServiceException.class,()->{
            User user = userService.createUser(username, lastName, password);
        },"Should have thrown user service exception");

        Mockito.verify(emailVerificationService,Mockito.times(1)).scheduleEmailConfirmation(Mockito.any(User.class));
    }

    @Test
    @DisplayName("Schedule email execution")
    void testCreateUser_whenUserCreated_schedulesEmailConfirmation(){

        //Arrange
        Mockito.when(usersRepository.saveUser(Mockito.any(User.class))).thenReturn(true);

        //with then when we invoke schedule confirm on email verification obje we want to call real method
        Mockito.doCallRealMethod().when(emailVerificationService).scheduleEmailConfirmation(Mockito.any(User.class));

        //Act
        User user = userService.createUser(username, lastName, password);

        //Assert
        Mockito.verify(emailVerificationService,Mockito.times(1)).scheduleEmailConfirmation(Mockito.any(User.class));


    }
}

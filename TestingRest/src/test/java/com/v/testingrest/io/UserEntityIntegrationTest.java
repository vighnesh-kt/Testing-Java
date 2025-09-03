package com.v.testingrest.io;

import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.UUID;

@DataJpaTest
public class UserEntityIntegrationTest {

    UserEntity userEntity;

    @Autowired
    TestEntityManager testEntityManager;

    @BeforeEach
    void setup() {
        userEntity = new UserEntity();
        userEntity.setUserId(UUID.randomUUID().toString());
        userEntity.setFirstName("Bruce");
        userEntity.setLastName("Wayne");
        userEntity.setEmail("im@batman.com");
        userEntity.setEncryptedPassword("12345678");
    }


    @Test
    void testUserEntity_whenValidDetailsProvided_shouldReturnStoredUser(){
        // Arrange
        //in before each

        // Act
        /*
        So if there is an issue with our entity object, it will not be persisted in a database.
        We will get an exception and our test method will fail the persist and flash method.
        It will return us back the source entity object, but it will be already updated with generated ID value
        so we can validate if the database ID was generated for this record and is present.
         */
        UserEntity storedUserEntity = testEntityManager.persistAndFlush(userEntity);

        // Assert
        //if multiple insert hence using > 0
        Assertions.assertTrue(storedUserEntity.getId() > 0);
        Assertions.assertEquals(userEntity.getUserId(), storedUserEntity.getUserId());
        Assertions.assertEquals(userEntity.getFirstName(), storedUserEntity.getFirstName());
        Assertions.assertEquals(userEntity.getLastName(), storedUserEntity.getLastName());
        Assertions.assertEquals(userEntity.getEmail(), storedUserEntity.getEmail());
        Assertions.assertEquals(userEntity.getEncryptedPassword(), storedUserEntity.getEncryptedPassword());


    }

    @Test
    //Persist entity if providing invalid user info
    void testUserEntity_whenFirstNameisTooLong_shouldThrowException(){
        //Arrange


        //Act and Assert

        Assertions.assertThrows(PersistenceException.class,
                ()->{
            testEntityManager.persistAndFlush(userEntity);
                },"Was expecting a PersistenceException to be thrown."

        );

    }

    @Test
    void testUserEntity_whenExistingUserIdProvided_shouldThrowException(){
        //Arrange
        UserEntity newEntity = new UserEntity();
        newEntity.setUserId("1");
        newEntity.setFirstName("Bruce");
        newEntity.setLastName("Wayne");
        newEntity.setEmail("im@batman.com");
        newEntity.setEncryptedPassword("12345678");

        testEntityManager.persistAndFlush(newEntity);
        userEntity.setUserId("1");

        //Act and Assert

        Assertions.assertThrows(PersistenceException.class,
                ()->{
                    testEntityManager.persistAndFlush(userEntity);
                },"Was expecting a PersistenceException to be thrown."

        );
    }

}

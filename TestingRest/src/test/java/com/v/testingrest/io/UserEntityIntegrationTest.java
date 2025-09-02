package com.v.testingrest.io;

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
        userEntity.setFirstName("Sergey");
        userEntity.setLastName("Kargopolov");
        userEntity.setEmail("test@test.com");
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

        //Act

    }

}

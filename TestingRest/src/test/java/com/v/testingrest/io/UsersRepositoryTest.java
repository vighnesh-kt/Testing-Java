package com.v.testingrest.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.UUID;

@DataJpaTest
public class UsersRepositoryTest {

    UserEntity userEntity;

    UserEntity userEntity2;



    //to persist entity
    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    UsersRepository usersRepository;

    @BeforeEach
    void setup() {
        userEntity = new UserEntity();
        userEntity.setUserId(UUID.randomUUID().toString());
        userEntity.setFirstName("Bruce");
        userEntity.setLastName("Wayne");
        userEntity.setEmail("im@batman.com");
        userEntity.setEncryptedPassword("12345678");
        testEntityManager.persistAndFlush(userEntity);

        userEntity2 = new UserEntity();
        userEntity2.setUserId(UUID.randomUUID().toString());
        userEntity2.setFirstName("Joker");
        userEntity2.setLastName("");
        userEntity2.setEmail("im@joker.com");
        userEntity2.setEncryptedPassword("12345678");
        testEntityManager.persistAndFlush(userEntity2);
    }



    @Test
    void testFIndByEmail_whenGivenCorrectEmail_ReturnUserEntity(){

        //Arrange

        //Act
        UserEntity storedUser = usersRepository.findByEmail(userEntity.getEmail());

        //Assert
        Assertions.assertEquals(userEntity.getEmail(),storedUser.getEmail() ,"Returned email doesn't match the expected value");



    }

    @Test
    void testFindByUserId_whenGivenCorrectUserId_ReturnUserEntity(){

        //Arrange

        //Act
        UserEntity storedUser = usersRepository.findByUserId(userEntity2.getUserId());


        //Assert
        Assertions.assertEquals(userEntity2.getUserId(),storedUser.getUserId() ,"Returned UserId doesn't match the expected value");


    }

    //testing jpql query
    @Test
    void testFindUsersWithEmailEndsWith_whenGiveEmailDomain_returnsUsersWithGivenDomain() {
        // Arrange
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(UUID.randomUUID().toString());
        userEntity.setEmail("test@gmail.com");
        userEntity.setEncryptedPassword("123456789");
        userEntity.setFirstName("Sergey");
        userEntity.setLastName("Kargopolov");
        testEntityManager.persistAndFlush(userEntity);

        String emailDomainName = "@gmail.com";

        // Act
        List<UserEntity> users = usersRepository.findUsersWithEmailEndingWith(emailDomainName);

        // Assert
        Assertions.assertEquals(1, users.size(),
                "There should be one user in the list");
        Assertions.assertTrue(users.get(0).getEmail().endsWith(emailDomainName),
                "User's email does not end with target domain name");

        users.stream().forEach(s-> System.out.println(s.getEmail()));
    }



}

package com.v.estore.service;

import com.v.estore.data.UsersRepository;
import com.v.estore.exception.UserServiceException;
import com.v.estore.model.User;

public class UserServiceImpl implements UserService {

    UsersRepository usersRepository;
    EmailVerificationService emailVerificationService;

    public UserServiceImpl(UsersRepository usersRepository,EmailVerificationService emailVerificationService) {
        this.usersRepository=usersRepository;
        this.emailVerificationService=emailVerificationService;
    }



    @Override
    public void setFirstName(String username) {

    }

    @Override
    public void setLastName(String lastName) {

    }

    @Override
    public void setPassword(String password) {

    }

    @Override
    public User createUser(String username, String lastName, String password) {

        if((username==null || lastName==null)){
            throw new IllegalArgumentException("Cannot be null");
        }
        User newUser=new User(username,lastName,password);
        boolean isUserCreated;
        try {
             isUserCreated = usersRepository.saveUser(newUser);
        }catch(RuntimeException ex){
            throw new UserServiceException(ex.getMessage());
        }

        if(!isUserCreated)  throw new UserServiceException("Could Not CreateUser") ;
        try{
            emailVerificationService.scheduleEmailConfirmation(newUser);
        }catch (RuntimeException ex){
          throw new UserServiceException(ex.getMessage());
        }
        return newUser;
    }

}

package com.v.estore.service;

import com.v.estore.data.UsersRepository;
import com.v.estore.exception.UserServiceException;
import com.v.estore.model.User;

public class UserServiceImpl implements UserService {

    UsersRepository usersRepository;

    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository=usersRepository;
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
        boolean isUserCreated = usersRepository.saveUser(newUser);
        if(!isUserCreated) throw new UserServiceException("Could Not CreateUser") ;
        return newUser;
    }

}

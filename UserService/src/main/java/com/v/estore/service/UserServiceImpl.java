package com.v.estore.service;

import com.v.estore.model.User;

public class UserServiceImpl implements UserService {

    public UserServiceImpl() {

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
        if(username.co)
        User newUser=new User(username,lastName,password);
        return newUser;
    }

}

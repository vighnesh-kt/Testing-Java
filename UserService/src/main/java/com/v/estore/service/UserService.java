package com.v.estore.service;

import com.v.estore.model.User;

public interface UserService {


    void setFirstName(String username);

    void setLastName(String lastName);

    void setPassword(String password);

    User createUser(String username, String lastName, String password);
}

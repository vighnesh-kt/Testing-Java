package com.v.estore.data;

import com.v.estore.model.User;

import java.util.HashMap;

public class UsersRepositoryImpl implements UsersRepository {

    HashMap<String,User> userData=new HashMap<>();
    @Override
    public boolean saveUser(User user) {
        if(user==null)return false;
        if(!userData.containsKey(user.getId())){
            userData.put(user.getId(),user);
            return true;
        }
        return false;
    }
}

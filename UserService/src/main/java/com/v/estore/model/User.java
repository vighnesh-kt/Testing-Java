package com.v.estore.model;

public class User {
    String username;
    String lastname;
    String password;


    public User(String username, String lastName, String password) {
        this.username=username;
        this.lastname=lastName;
        this.password=password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", lastname='" + lastname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

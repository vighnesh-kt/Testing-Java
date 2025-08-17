package com.v.estore.model;

import java.util.UUID;

public class User {

    String id;
    String username;
    String lastname;
    String password;


    public User(String username, String lastName, String password) {
        this.id= UUID.randomUUID().toString();
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void main(String[] args) {
        char c=95;

        System.out.println(c);
    }
}

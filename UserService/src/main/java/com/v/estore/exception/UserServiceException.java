package com.v.estore.exception;

public class UserServiceException extends RuntimeException {

    String message;
    public UserServiceException(String message) {
        super("Could Not CreateUser");
        this.message=message;
    }
}

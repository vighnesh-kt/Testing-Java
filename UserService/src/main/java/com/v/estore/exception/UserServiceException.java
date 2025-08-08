package com.v.estore.exception;

public class UserServiceException extends Throwable {

    String message;
    public UserServiceException(String message) {
        super("Could Not CreateUser");
        this.message=message;
    }
}

package com.v.testingrestcontroller.exceptions;

public class UsersServiceException extends RuntimeException{
    public UsersServiceException(String message)
    {
        super(message);
    }
}
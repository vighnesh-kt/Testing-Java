package com.v.estore.exception;

public class EmailVerificationServiceException extends RuntimeException{

    String message;

    public EmailVerificationServiceException(String message) {
        super("Could Not send Email");
        this.message=message;
    }

}

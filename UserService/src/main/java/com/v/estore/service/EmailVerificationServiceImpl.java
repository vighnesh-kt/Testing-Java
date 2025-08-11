package com.v.estore.service;

import com.v.estore.model.User;

public class EmailVerificationServiceImpl implements EmailVerificationService {
    @Override
    public void scheduleEmailConfirmation(User user) {
        System.out.println("Sending message to "+user.getUsername());
    }
}

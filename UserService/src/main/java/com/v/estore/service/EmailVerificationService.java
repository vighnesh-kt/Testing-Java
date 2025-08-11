package com.v.estore.service;

import com.v.estore.model.User;

public interface EmailVerificationService {

    void scheduleEmailConfirmation(User user);
}

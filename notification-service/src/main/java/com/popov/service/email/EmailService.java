package com.popov.service.email;

import com.popov.EmailType;

import java.util.Properties;

public interface EmailService {

    void sendEmail(String email, EmailType emailType, Properties mailProperties);

}

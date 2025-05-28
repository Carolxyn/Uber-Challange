package com.uber.email_service.core;

public interface EmailSendUserCase {
    void sendEmail(String to, String subject, String body);
}

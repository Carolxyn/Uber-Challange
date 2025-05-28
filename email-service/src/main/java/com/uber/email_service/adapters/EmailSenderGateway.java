package com.uber.email_service.adapters;

public interface EmailSenderGateway {
    void sendMail(String to, String subject, String body);
}

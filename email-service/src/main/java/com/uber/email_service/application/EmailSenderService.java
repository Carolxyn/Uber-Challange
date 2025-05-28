package com.uber.email_service.application;


import com.uber.email_service.adapters.EmailSenderGateway;
import com.uber.email_service.core.EmailSendUserCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService implements EmailSendUserCase {

    private final EmailSenderGateway emailSenderGateway;

    @Autowired
    public EmailSenderService(EmailSenderGateway emailGateway){
        this.emailSenderGateway = emailGateway;
    }



    @Override
    public void sendEmail(String to, String subject, String body) {
        this.emailSenderGateway.sendMail(to, subject, body);

    }
}

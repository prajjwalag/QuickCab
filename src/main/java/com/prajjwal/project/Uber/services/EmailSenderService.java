package com.prajjwal.project.Uber.services;

public interface EmailSenderService {
    
    void sendEmail(String toEmail, String subject, String body);

    void sendEmail(String[] toEmail, String subject, String body);
}

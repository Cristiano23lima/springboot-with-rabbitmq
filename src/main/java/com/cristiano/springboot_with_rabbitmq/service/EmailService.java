package com.cristiano.springboot_with_rabbitmq.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendEmail(String message, String to, String name){
        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        simpleMessage.setFrom("devcristiano.rodrigues@gmail.com");
        simpleMessage.setTo(to);
        simpleMessage.setSubject("Message Test to "+ name +" [No reply]");
        simpleMessage.setText(message);

        mailSender.send(simpleMessage);
    }
}

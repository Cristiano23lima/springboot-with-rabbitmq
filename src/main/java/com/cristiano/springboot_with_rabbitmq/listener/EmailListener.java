package com.cristiano.springboot_with_rabbitmq.listener;

import com.cristiano.springboot_with_rabbitmq.dto.MessageDTO;
import com.cristiano.springboot_with_rabbitmq.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailListener {

    private final EmailService emailService;

    @RabbitListener(queues = "${spring.rabbitmq.queue-name}")
    public void consume(MessageDTO message){
        emailService.sendEmail(message.getMessage(), message.getEmail(), message.getName());
    }
}

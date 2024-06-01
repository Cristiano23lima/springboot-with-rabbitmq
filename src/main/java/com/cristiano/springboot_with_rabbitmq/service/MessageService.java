package com.cristiano.springboot_with_rabbitmq.service;

import com.cristiano.springboot_with_rabbitmq.dto.MessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final RabbitTemplate rabbitTemplate;
    private final String exchangeName;

    private static final String ROUTING_KEY = "email.notification.message";

    public MessageService(
            @Value("${spring.rabbitmq.topic-exchange-name}") String exchangeName,
            RabbitTemplate rabbitTemplate
    ){
        this.rabbitTemplate = rabbitTemplate;
        this.exchangeName = exchangeName;
    }

    public void send(MessageDTO message){
        this.rabbitTemplate.convertAndSend(exchangeName, ROUTING_KEY, message);
    }

}

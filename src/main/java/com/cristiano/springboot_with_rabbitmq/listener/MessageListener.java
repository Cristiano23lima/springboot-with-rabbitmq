package com.cristiano.springboot_with_rabbitmq.listener;

import com.cristiano.springboot_with_rabbitmq.dto.MessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageListener {

    @RabbitListener(queues = "${spring.rabbitmq.queue-name}")
    public void consume(MessageDTO message){
        log.info("Message: {}, email: {}, name: {}", message.getMessage(), message.getEmail(), message.getName());
    }
}

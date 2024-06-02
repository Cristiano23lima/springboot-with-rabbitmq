package com.cristiano.springboot_with_rabbitmq.listener;

import com.cristiano.springboot_with_rabbitmq.config.RabbitTestContainer;
import com.cristiano.springboot_with_rabbitmq.dto.MessageDTO;
import com.cristiano.springboot_with_rabbitmq.util.DataGenerator;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.time.Duration;
import java.util.concurrent.Callable;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(OutputCaptureExtension.class)
class MessageListenerTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String ROUTING_KEY = "message.notification";

    @Test
    void consume_runSuccessfully_whenReceiveMessage(CapturedOutput output){
        MessageDTO message = DataGenerator.buildMessageValid();

        rabbitTemplate.convertAndSend(
                rabbitTemplate.getExchange(),
                ROUTING_KEY,
                message
        );

        Awaitility.await()
                .atMost(Duration.ofSeconds(10))
                .until(isRunningRabbit(output), is(true));

        assertTrue(output.getOut().contains("Message: "+message.getMessage()));
        assertTrue(output.getOut().contains("email: "+message.getEmail()));
        assertTrue(output.getOut().contains("name: "+message.getName()));
    }

    private Callable<Boolean> isRunningRabbit(CapturedOutput output){
        return () -> output.getOut().contains("Message: ");
    }
}

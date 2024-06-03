package com.cristiano.springboot_with_rabbitmq.listener;

import com.cristiano.springboot_with_rabbitmq.config.RabbitTestContainer;
import com.cristiano.springboot_with_rabbitmq.dto.MessageDTO;
import com.cristiano.springboot_with_rabbitmq.util.DataGenerator;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
class MessageListenerTest extends RabbitTestContainer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @BeforeEach
    void setup(){
        Logger logger = LoggerFactory.getLogger(MessageListenerTest.class);
        logger.info("RabbitMQ Container host: {}", container.getHost());
        logger.info("RabbitMQ Container port: {}", container.getMappedPort(5672));
        logger.info("RabbitMQ Container is running: {}", container.isRunning());

        Awaitility.await()
                .atMost(Duration.ofSeconds(30))
                .until(isRunningRabbit(), is(true));
    }

    @Test
    void consume_runSuccessfully_whenReceiveMessage(CapturedOutput output){
        MessageDTO message = DataGenerator.buildMessageValid();

        rabbitTemplate.convertAndSend(
                "sent-message",
                ROUTING_KEY,
                message
        );

        Awaitility.await()
                .atMost(Duration.ofSeconds(10))
                .until(messageConsumed(output), is(true));

        assertTrue(output.getOut().contains("Message: "+message.getMessage()));
        assertTrue(output.getOut().contains("email: "+message.getEmail()));
        assertTrue(output.getOut().contains("name: "+message.getName()));
    }

    private Callable<Boolean> messageConsumed(CapturedOutput output){
        return () -> output.getOut().contains("Message: ");
    }

    private Callable<Boolean> isRunningRabbit(){
        return () -> RabbitTestContainer.container.isRunning();
    }
}

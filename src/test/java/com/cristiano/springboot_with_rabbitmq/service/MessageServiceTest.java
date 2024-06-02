package com.cristiano.springboot_with_rabbitmq.service;

import com.cristiano.springboot_with_rabbitmq.config.RabbitTestContainer;
import com.cristiano.springboot_with_rabbitmq.dto.MessageDTO;
import com.cristiano.springboot_with_rabbitmq.util.DataGenerator;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.Duration;
import java.util.concurrent.Callable;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest
@ExtendWith(OutputCaptureExtension.class)
class MessageServiceTest implements RabbitTestContainer {

    @InjectMocks
    private MessageService messageService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @BeforeEach
    void setup(){
        ReflectionTestUtils.setField(messageService, "exchangeName", rabbitTemplate.getExchange());
        ReflectionTestUtils.setField(messageService, "rabbitTemplate", rabbitTemplate);

        Awaitility.await()
                        .atMost(Duration.ofSeconds(10))
                                .until(isRunningRabbit(), is(true));
    }

    @Test
    void send_runSuccessfully_whenPassedMessageDto(CapturedOutput output){
        assertDoesNotThrow(() -> messageService.send(DataGenerator.buildMessageValid()));
    }

    private Callable<Boolean> isRunningRabbit(){
        return () -> RabbitTestContainer.container.isRunning();
    }
}

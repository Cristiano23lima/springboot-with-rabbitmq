package com.cristiano.springboot_with_rabbitmq.controller;

import com.cristiano.springboot_with_rabbitmq.config.RabbitTestContainer;
import com.cristiano.springboot_with_rabbitmq.dto.MessageDTO;
import com.cristiano.springboot_with_rabbitmq.util.DataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class MessageControllerTest implements RabbitTestContainer {

    @Autowired
    private WebTestClient webTestClient;

    private MessageDTO message;

    @BeforeEach
    void setup(){
        message = DataGenerator.buildMessageValid();
    }

    @Test
    void sendMessage_return200OK_whenSuccessfully(){
        webTestClient.post()
                .uri("/api/v1/message")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(message)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class)
                .isEqualTo("Message sent successfully!");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void sendMessage_return400BadRequest_whenEmailIsInvalid(String input){
        message.setEmail(input);
        webTestClient.post()
                .uri("/api/v1/message")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(message)
                .exchange()
                .expectStatus()
                .isBadRequest();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void sendMessage_return400BadRequest_whenNameIsInvalid(String input){
        message.setName(input);
        webTestClient.post()
                .uri("/api/v1/message")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(message)
                .exchange()
                .expectStatus()
                .isBadRequest();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void sendMessage_return400BadRequest_whenMessageIsInvalid(String input){
        message.setMessage(input);
        webTestClient.post()
                .uri("/api/v1/message")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(message)
                .exchange()
                .expectStatus()
                .isBadRequest();
    }
}

package com.cristiano.springboot_with_rabbitmq.controller;

import com.cristiano.springboot_with_rabbitmq.service.MessageService;
import com.cristiano.springboot_with_rabbitmq.dto.MessageDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @Tag(name = "Message API", description = "API responsible for sending messages to RabbitMQ")
    @PostMapping
    public ResponseEntity<String> sendMessage(
            @RequestBody MessageDTO message
    ){
        this.messageService.send(message);

        return ResponseEntity.ok().body("Message sent successfully!");
    }
}

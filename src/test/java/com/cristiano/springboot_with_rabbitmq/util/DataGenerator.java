package com.cristiano.springboot_with_rabbitmq.util;

import com.cristiano.springboot_with_rabbitmq.dto.MessageDTO;

public class DataGenerator {
    public static MessageDTO buildMessageValid(){
        return MessageDTO.builder()
                .message("Message test")
                .name("Teste joão")
                .email("example@example.com").build();
    }
}

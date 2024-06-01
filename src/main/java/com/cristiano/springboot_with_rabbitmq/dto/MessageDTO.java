package com.cristiano.springboot_with_rabbitmq.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MessageDTO implements Serializable {
    private String message;
    private String name;
    private String email;
}

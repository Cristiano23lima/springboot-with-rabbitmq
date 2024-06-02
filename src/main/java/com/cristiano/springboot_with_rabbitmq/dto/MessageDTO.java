package com.cristiano.springboot_with_rabbitmq.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class MessageDTO implements Serializable {
    @NotBlank(message = "Field message is required")
    private String message;
    @NotBlank(message = "Field name is required")
    private String name;
    @NotBlank(message = "Field email is required")
    private String email;
}

package com.cristiano.springboot_with_rabbitmq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class SpringbootWithRabbitmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWithRabbitmqApplication.class, args);
	}

}

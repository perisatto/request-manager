package com.perisatto.fiapprj.request_manager;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class RequestManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RequestManagerApplication.class, args);
	}
}

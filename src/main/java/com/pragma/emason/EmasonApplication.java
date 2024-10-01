package com.pragma.emason;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EmasonApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmasonApplication.class, args);
	}

}

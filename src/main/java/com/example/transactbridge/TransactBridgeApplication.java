package com.example.transactbridge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.transactbridge")
public class TransactBridgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactBridgeApplication.class, args);
	}

}

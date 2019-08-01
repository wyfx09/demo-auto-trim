package com.example.hlw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.hlw")
public class DemoTrimApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoTrimApplication.class, args);
	}

}

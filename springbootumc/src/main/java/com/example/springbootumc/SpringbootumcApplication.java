package com.example.springbootumc;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringbootumcApplication {

	public static void main(String[] args) {
		BasicConfigurator.configure();
		SpringApplication.run(SpringbootumcApplication.class, args);
	}

}

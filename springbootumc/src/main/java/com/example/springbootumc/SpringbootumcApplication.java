package com.example.springbootumc;

import com.example.springbootumc.dao.UserDao;
import com.example.springbootumc.model.UserBean;
import org.apache.log4j.BasicConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringbootumcApplication {


	public static void main(String[] args) {

		SpringApplication.run(SpringbootumcApplication.class, args);
	}

}

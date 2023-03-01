package com.essienmichael.cocclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class CocclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(CocclientApplication.class, args);
	}

}

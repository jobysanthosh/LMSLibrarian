package com.lms.LMSBorrower;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
public class LmsBorrowerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsBorrowerApplication.class, args);
	}
}

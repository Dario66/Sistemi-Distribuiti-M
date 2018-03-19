package com.example.EurekaServer3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


 

@SpringBootApplication
@EnableEurekaServer
public class EurekaServer3Application {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(EurekaServer3Application.class);
        application.setAdditionalProfiles("peer3");
        application.run(args);
	}

}
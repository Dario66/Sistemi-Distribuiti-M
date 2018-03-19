package com.example.EurekaPeer2;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
public class EurekaServer {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(EurekaServer.class);
        application.setAdditionalProfiles("peer2");
        application.run(args);
	}

}
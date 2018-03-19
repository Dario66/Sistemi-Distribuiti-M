package com.example.EurekaPeer1;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.support.ClassPathXmlApplicationContext;


@SpringBootApplication
@EnableEurekaServer
public class EurekaServerMicroServiceApplication /*extends SpringBootServletInitializer*/ {

    public static void main(String[] args) {
    	  SpringApplication application = new SpringApplication(EurekaServerMicroServiceApplication.class);
          application.setAdditionalProfiles("peer1");
          application.run(args);
    }
   
}
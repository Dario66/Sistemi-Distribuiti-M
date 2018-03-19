package com.example.BookService1;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class BookService1Application {
	private static Log logger = LogFactory.getLog(BookService1Application.class);

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(BookService1Application.class);
        application.setAdditionalProfiles("Bserver-1");
        application.run(args);
    }
    @RequestMapping("/name")
    public String text() {
    	
    	logger.info("book1");
        return "BookService_OK";
    }
   
}

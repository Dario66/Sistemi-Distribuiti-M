package com.example.BookService2;

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
public class BookService2Application {
	private static Log logger = LogFactory.getLog(BookService2Application.class);

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(BookService2Application.class);
        application.setAdditionalProfiles("Bserver-2");
        application.run(args);
    }

    @RequestMapping("/name")
    public String text() {
    	
    	logger.info("book2");
        return "BookService2_OK";
    }
}

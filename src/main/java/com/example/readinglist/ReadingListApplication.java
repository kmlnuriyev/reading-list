package com.example.readinglist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This annotation enables auto-configuration and component-scanning.
 * And combines three annotation:
 * @Configuration (Spring's)
 * @ComponentScan (Spring's)
 * @EnableAutoConfiguration (Spring Boot's)
 */
@SpringBootApplication
public class ReadingListApplication {

    public static void main(String[] args) {
        // Bootstrap the application
        SpringApplication.run(ReadingListApplication.class, args);
    }

}

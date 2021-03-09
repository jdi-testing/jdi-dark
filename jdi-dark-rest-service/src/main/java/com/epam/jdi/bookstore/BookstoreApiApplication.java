package com.epam.jdi.bookstore;

import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import static org.apache.logging.log4j.LogManager.getLogger;

@EntityScan(basePackages = {"com.epam.jdi.bookstore.model"})
@SpringBootApplication
public class BookstoreApiApplication {
    public static Logger logger = getLogger("JDI");

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApiApplication.class, args);
    }

}

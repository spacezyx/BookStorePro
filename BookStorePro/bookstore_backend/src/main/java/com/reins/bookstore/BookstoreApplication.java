package com.reins.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class BookstoreApplication {
    public static void main(String[] args) {SpringApplication.run(BookstoreApplication.class, args);
    }

}

package com.reins.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement

@Component
public class BookstoreApplication {

    public static void main(String[] args) {SpringApplication.run(BookstoreApplication.class, args);
    }



}

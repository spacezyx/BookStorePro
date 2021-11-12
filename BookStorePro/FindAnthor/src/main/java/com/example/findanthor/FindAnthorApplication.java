package com.example.findanthor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class FindAnthorApplication {

    public static void main(String[] args) {
        SpringApplication.run(FindAnthorApplication.class, args);
    }

}

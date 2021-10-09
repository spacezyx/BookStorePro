package com.reins.bookstore.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VisitControllerTest {

    @Autowired
    private VisitController visitController;

    @Test
    void visitTimes() {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> visitController.visitTimes()).start();
        }
    }
}
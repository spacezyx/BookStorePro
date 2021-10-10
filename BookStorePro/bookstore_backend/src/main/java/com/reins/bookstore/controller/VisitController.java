package com.reins.bookstore.controller;

import com.reins.bookstore.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class VisitController {
    @Autowired
    private VisitService visitService;

    @RequestMapping("/visitTimes")
    public void visitTimes() {
        visitService.visitTimes();
    }

    @RequestMapping("/getVists")
    public Integer getVists() {
        return visitService.getVists();
    }


}

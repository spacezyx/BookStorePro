package com.example.findanthor.Controller;

import com.example.findanthor.Service.FindAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FindAuthorController {
    @Autowired
    private FindAuthorService findAuthorService;

    @RequestMapping("/FindAuthor")
    public String FindAuthor(@RequestParam("name") String name){
        System.out.println("controller");
        return findAuthorService.FindAuthor(name);
    }
}

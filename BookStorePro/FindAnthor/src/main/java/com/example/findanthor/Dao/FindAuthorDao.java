package com.example.findanthor.Dao;

import com.example.findanthor.Repository.FindAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FindAuthorDao {
    @Autowired
    private FindAuthorRepository findAuthorRepository;

    public String FindAuthor(String name) {
        System.out.println("dao");
        return findAuthorRepository.FindAuthor(name);
    }
}

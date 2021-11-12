package com.example.findanthor.Service;

import com.example.findanthor.Dao.FindAuthorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindAuthorService {
    @Autowired
    private FindAuthorDao findAuthorDao;

    public String FindAuthor(String name) {
        System.out.println("service");
        return findAuthorDao.FindAuthor(name);
    }
}

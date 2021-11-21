package com.reins.bookstore.controller;

import com.reins.bookstore.entity.Book;
import com.reins.bookstore.entity.Image;
import com.reins.bookstore.entity.vo.BookStatistic;
import com.reins.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;


@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/getBooks")
    public List<Book> getBooks(@RequestBody Map<String, String> params) {
        return bookService.getBooks();
    }

    @RequestMapping("/getByTags")
    public List<Book> getByTags(@RequestParam("tag") String tag){return bookService.getByTags(tag);}

    @RequestMapping("/getBook")
    public Book getBook(@RequestParam("id") Integer id){
        return bookService.findBookById(id);
    }

    @RequestMapping("/getBookImage")
    public Image getBookImage(@RequestParam("id") Integer id){
        return bookService.findImageByBookId(id);
    }

    @RequestMapping("/getBookStatistic")
    @PreAuthorize("hasRole('ADMIN')")
    public BookStatistic getBookStatistic(@RequestBody Map<String, String> params) {return bookService.getBookStatistic();}

    @RequestMapping("/searchDescriptions")
    public List<Book> searchDescriptions(@RequestParam("text") String text) throws IOException, org.apache.lucene.queryparser.classic.ParseException {
        return bookService.searchDescriptions(text);
    }
}

package com.reins.bookstore.service;

import com.reins.bookstore.entity.Book;
import com.reins.bookstore.entity.Image;
import com.reins.bookstore.entity.vo.BookStatistic;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;


public interface BookService {

    Book findBookById(Integer id);

    List<Book> getBooks();

    Image findImageByBookId(Integer id);

    BookStatistic getBookStatistic();

    void decreaseInventory(Integer num,Integer book_id) throws Exception;

    List<Book> searchDescriptions(String text) throws IOException, org.apache.lucene.queryparser.classic.ParseException;

    //建个索引
    void createIndex() throws IOException;
}

package com.reins.bookstore.dao;

import com.reins.bookstore.entity.Book;
import com.reins.bookstore.entity.Image;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface BookDao {
    Book findOne(Integer id);

    List<Book> getBooks();

    Image getBookImage(Integer id);

    Integer getBookNumber();

    Integer getBookInventorySum();

    Integer getBookInventory(Integer id);

    void decreaseInventory(Integer num,Integer id);

    List<Book> getByTags(String Tag);
}

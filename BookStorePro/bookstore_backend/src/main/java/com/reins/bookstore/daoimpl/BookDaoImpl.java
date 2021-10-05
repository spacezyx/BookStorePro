package com.reins.bookstore.daoimpl;

import com.alibaba.fastjson.JSONArray;
import com.reins.bookstore.Utils.RedisUtil;
import com.reins.bookstore.dao.BookDao;
import com.reins.bookstore.entity.Book;
import com.reins.bookstore.entity.Image;
import com.reins.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

//新增Redis缓存
@Repository
public class BookDaoImpl implements BookDao {



    @Autowired
    private BookRepository bookRepository;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public Book findOne(Integer id) {
        Book book;
        System.out.println("Searching Book: " + id + " in Redis");
        Object p = redisUtil.get("book" + id);
        if (p == null) {
            System.out.println("Book: " + id + " is not in Redis");
            System.out.println("Searching Book: " + id + " in MySQL");
            book = bookRepository.getOne(id);
            redisUtil.set("book" + id, JSONArray.toJSON(book));
        } else {
            book = JSONArray.parseObject(p.toString(), Book.class);
            System.out.println("Book: " + id + " is in Redis");
        }
        return book;
    }

    @Override
    public List<Book> getBooks() {
        List<Book> booklet;
        System.out.println("Searching booklet: "  + " in Redis");
        Object p = redisUtil.lGetIndex("booklet",-1);
        if (p == null) {
            System.out.println("booklet: " + " is not in Redis");
            System.out.println("Searching booklet: " + " in MySQL");
            booklet = bookRepository.getBooks();
            redisUtil.lSet("booklet" , JSONArray.toJSON(booklet));
        } else {
            booklet = JSONArray.parseArray(p.toString(), Book.class);
            System.out.println("booklet: "  + " is in Redis");
            System.out.println(booklet);
        }
        return booklet;
    }

    @Override
    public Image getBookImage(Integer id) {
        Image image;
        System.out.println("Searching image: " + id + " in Redis");
        Object p = redisUtil.get("image" + id);
        if (p == null) {
            System.out.println("image: " + id + " is not in Redis");
            System.out.println("Searching image: " + id + " in MySQL");
            image = bookRepository.getOne(id).getBookImage();
            redisUtil.set("image" + id, JSONArray.toJSON(image));
        } else {
            image = JSONArray.parseObject(p.toString(), Image.class);
            System.out.println("image: " + id + " is in Redis");
        }
        return image;
    }

    @Override
    public Integer getBookNumber() {
        Integer bookNumber;
        System.out.println("Searching bookNumber: "  + " in Redis");
        Object p = redisUtil.get("bookNumber");
        if (p == null) {
            System.out.println("bookNumber: " + " is not in Redis");
            System.out.println("Searching bookNumber: " + " in MySQL");
            bookNumber = bookRepository.getBookNumber();
            redisUtil.set("bookNumber" , JSONArray.toJSON(bookNumber));
        } else {
            bookNumber = JSONArray.parseObject(p.toString(), Integer.class);
            System.out.println("bookNumber: "  + " is in Redis");
        }
        return bookNumber;
    }

    @Override
    public Integer getBookInventorySum() {
        Integer inventorySum;
        System.out.println("Searching inventorySum: "  + " in Redis");
        Object p = redisUtil.get("inventorySum");
        if (p == null) {
            System.out.println("inventorySum: " + " is not in Redis");
            System.out.println("Searching inventorySum: " + " in MySQL");
            inventorySum = bookRepository.getBookInventorySum();
            redisUtil.set("inventorySum" , JSONArray.toJSON(inventorySum));
        } else {
            inventorySum = JSONArray.parseObject(p.toString(), Integer.class);
            System.out.println("inventorySum: "  + " is in Redis");
        }
        return inventorySum;
    }

    @Override
    public Integer getBookInventory(Integer id){
        Integer inventory;
        System.out.println("Searching inventory: " + id + " in Redis");
        Object p = redisUtil.get("inventory" + id);
        if (p == null) {
            System.out.println("inventory: " + id + " is not in Redis");
            System.out.println("Searching inventory: " + id + " in MySQL");
            inventory = bookRepository.getBookInventory(id);
            redisUtil.set("inventory" + id, JSONArray.toJSON(inventory));
        } else {
            inventory = JSONArray.parseObject(p.toString(), Integer.class);
            System.out.println("inventory: " + id + " is in Redis");
        }
        return inventory;
    }

    @Override
    public void decreaseInventory(Integer num,Integer id){
        Book book;
        bookRepository.decreaseInventory(num,id);
        System.out.println("Searching book: " + id + " in Redis");
        book = bookRepository.getOne(id);
        redisUtil.set("book" + id, JSONArray.toJSON(book));
    }
}

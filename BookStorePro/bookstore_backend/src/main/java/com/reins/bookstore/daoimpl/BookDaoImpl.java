package com.reins.bookstore.daoimpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.reins.bookstore.Utils.RedisUtil;
import com.reins.bookstore.dao.BookDao;
import com.reins.bookstore.entity.Book;
import com.reins.bookstore.entity.Descriptions;
import com.reins.bookstore.entity.Image;
import com.reins.bookstore.entity.Tags;
import com.reins.bookstore.repository.BookRepository;
import com.reins.bookstore.repository.DescriptionsRepository;
import com.reins.bookstore.repository.TagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

//新增Redis缓存
@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private DescriptionsRepository descriptionsRepository;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    private TagsRepository tagsRepository;

    @Override
    public Book findOne(Integer id) {
        Book book;
        System.out.println(id);
        System.out.println("Searching Book: " + id + " in Redis");
        Map<Object,Object> p = redisUtil.hmget("book" + id);
        System.out.println(p);
        if (p.size() == 0) {
            System.out.println("Book: " + id + " is not in Redis");
            System.out.println("Searching Book: " + id + " in MySQL");
            book = bookRepository.getById(id);
            Optional<Descriptions> icon = descriptionsRepository.findById(id);
            if (icon.isPresent()){
                System.out.println("Not Null " + id);
                book.setDescription(icon.get());
            }
            else{
                book.setDescription(null);
                System.out.println("It's Null");
            }
            System.out.println(book);
            Map<String,Object> map = new HashMap<>();
            map.put("bookId",book.getBookId());
            map.put("isbn",book.getIsbn());
            map.put("name",book.getName());
            map.put("tag",book.getTag());
            map.put("author",book.getAuthor());
            map.put("price",book.getPrice());
            map.put("description",book.getDescription());
            map.put("inventory",book.getInventory());
            map.put("bookImage",book.getBookImage());
            System.out.println(map);
            redisUtil.hmset("book" + id, map);
        } else {
            book = JSONArray.parseObject(JSON.toJSONString(p), Book.class);
            System.out.println("Book: " + id + " is in Redis");
            System.out.println(book);
        }
        return book;
    }

    //直接调用findOne函数 Redis存在的数据将通过Redis获取 并将补齐Redis不存在的内容
    @Override
    public List<Book> getBooks(){
        Integer booknum = getBookNumber();
        System.out.println(booknum);
        List<Book> booklet = new ArrayList<>();
        for(int i=1;i<=booknum;i++){
            Book book = findOne(i);
            booklet.add(book);
        }
        return booklet;
    }

    @Override
    public Image getBookImage(Integer id) {
        Image image;
        Map<Object,Object> p = redisUtil.hmget("book" + id);
        System.out.println(p);
        if (p.size() == 0) {
            Book book = findOne(id);
            image  = book.getBookImage();
        } else {
            Book book = JSONArray.parseObject(JSON.toJSONString(p), Book.class);
            image = book.getBookImage();
        }
        return image;
    }

    //此数据和book table的所有数据相关  但与book的属性关联不大，故单独存一个关键字
    @Override
    public Integer getBookNumber() {
        Integer bookNumber;
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

    //不是常用数据，且此处与book table的所有数据相关  故不存进Redis
    @Override
    public Integer getBookInventorySum() {
        Integer inventorySum = bookRepository.getBookInventorySum();
        return inventorySum;
    }

    @Override
    public Integer getBookInventory(Integer id){
        Integer inventory;
        Map<Object,Object> p = redisUtil.hmget("book" + id);
        if (p.size() == 0) {
            Book book = findOne(id);
            inventory = book.getInventory();
        } else {
            Book book = JSONArray.parseObject(JSON.toJSONString(p), Book.class);
            inventory = book.getInventory();
            System.out.println("inventory: " + id + " is in Redis");
        }
        return inventory;
    }

    @Override
    @Transactional
    public void decreaseInventory(Integer num,Integer id){
        //更新Inventory  保证一致性加上@Transactional
        bookRepository.decreaseInventory(num,id);
        redisUtil.hdecr("book"+id,"inventory",num);
    }

    @Override
    public List<Book> getByTags(String tag){
        List<Tags> tags = tagsRepository.findtags(tag);
        System.out.println(tags.toString());
        List<Book> result = new ArrayList<>();
        Set<Integer> result_id = new HashSet<>();
        Integer size = tags.size();
        for(int i = 0; i < size; i++){
            Set<Integer> tmp = bookRepository.getByTags(tags.get(i).getTag());
            result_id.addAll(tmp);
        }
        for(Integer id:result_id){
            Book tmp = findOne(id);
            result.add(tmp);
        }
        return result;
    }
}

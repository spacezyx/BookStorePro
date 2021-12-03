package com.reins.bookstore.serviceimpl;

import com.reins.bookstore.dao.BookDao;
import com.reins.bookstore.entity.Book;
import com.reins.bookstore.entity.Image;
import com.reins.bookstore.service.BookService;
import com.reins.bookstore.entity.vo.BookStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName BookServiceImpl
 * @Description the Implement of BookService
 * @Author thunderBoy
 * @Date 2019/11/6 16:04
 */

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public Book findBookById(Integer id){
        return bookDao.findOne(id);
    }

    @Override
    public List<Book> getBooks() {
        return bookDao.getBooks();
    }

    @Override
    public Image findImageByBookId(Integer id) {
        return bookDao.getBookImage(id);
    }

    @Override
    public BookStatistic getBookStatistic() {
        BookStatistic statistic = new BookStatistic();
        statistic.setBookNumber(bookDao.getBookNumber());
        statistic.setBookInventorySum(bookDao.getBookInventorySum());
        return  statistic;
    }

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    @Override
    public void decreaseInventory(Integer num,Integer book_id) throws Exception{
        Integer inventory = bookDao.getBookInventory(book_id);
        System.out.println(inventory);
        if(num > inventory){
            throw new Exception("No Enough Books");
        }
        else if(bookDao.findOne(book_id)==null){
            throw new Exception("The Book Not Exists");
        }
        else{
            bookDao.decreaseInventory(num,book_id);
        }
    }
}

package com.reins.bookstore.serviceimpl;

import com.reins.bookstore.dao.BookDao;
import com.reins.bookstore.entity.BookItem;
import com.reins.bookstore.entity.CartResult;
import com.reins.bookstore.entity.OrderInfo;
import com.reins.bookstore.entity.OrderQueue;
import com.reins.bookstore.dao.CartDao;
import com.reins.bookstore.dao.OrderDao;
import com.reins.bookstore.service.BookService;
import com.reins.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//JMS异步处理订单程序 service B：监听OrderQueue，一旦读到消息立即进行处理，将数据写入数据库
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private CartDao cartDao;

    @Autowired
    private BookService bookService;

    @Autowired
    private OrderDao orderDao;

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void addOrder(OrderQueue order) throws Exception{
        Integer user_id = order.getUser_id();
        List<BookItem> bookItems = order.getBookItems();
        Integer order_id = orderDao.addOrder(user_id);
        int size = bookItems.size();

        for(int i=0;i<size;i++){
            Integer book_id = bookItems.get(i).getBook_id();
            Integer num = bookItems.get(i).getNum();
            orderDao.addOrderInfo(order_id,book_id,num);
            bookService.decreaseInventory(num,book_id);
        }
        cartDao.cleanCartByUser_Id(user_id);
    }

    @Override
    public List<CartResult> getOrder(Integer user_id){
        return orderDao.getOrder(user_id);
    }
}

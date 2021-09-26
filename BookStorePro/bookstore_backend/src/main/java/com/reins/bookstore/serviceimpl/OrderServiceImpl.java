package com.reins.bookstore.serviceimpl;

import com.reins.bookstore.entity.BookItem;
import com.reins.bookstore.entity.CartResult;
import com.reins.bookstore.entity.OrderInfo;
import com.reins.bookstore.entity.OrderQueue;
import com.reins.bookstore.dao.CartDao;
import com.reins.bookstore.dao.OrderDao;
import com.reins.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//JMS异步处理订单程序 service B：监听OrderQueue，一旦读到消息立即进行处理，将数据写入数据库
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private CartDao cartDao;

    @Autowired
    private OrderDao orderDao;

    @JmsListener(destination = "order")
    public void addOrder(OrderQueue order){
        Integer user_id = order.getUser_id();
        List<BookItem> bookItems = order.getBookItems();
        orderDao.addOrder(user_id,bookItems);
        cartDao.cleanCartByUser_Id(user_id);
    }

    @Override
    public List<CartResult> getOrder(Integer user_id){
        return orderDao.getOrder(user_id);
    }
}

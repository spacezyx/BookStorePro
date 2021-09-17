package com.reins.bookstore.serviceimpl;

import com.reins.bookstore.entity.OrderQueue;
import com.reins.bookstore.dao.CartDao;
import com.reins.bookstore.dao.OrderDao;
import com.reins.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private CartDao cartDao;

    @Autowired
    private OrderDao orderDao;

    @JmsListener(destination = "order")
    public void addOrder(OrderQueue order){
        Integer user_id = order.getBody();
        orderDao.addOrder(user_id);
        cartDao.cleanCartByUser_Id(user_id);
    }
}

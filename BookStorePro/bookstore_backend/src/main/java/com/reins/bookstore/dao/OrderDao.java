package com.reins.bookstore.dao;

import com.reins.bookstore.entity.BookItem;
import com.reins.bookstore.entity.CartResult;
import com.reins.bookstore.entity.OrderInfo;

import java.util.List;

public interface OrderDao {
    Integer addOrder(Integer user_id);

    void addOrderInfo(Integer order_id,Integer book_id,Integer num);

    List<CartResult> getOrder(Integer user_id);
}

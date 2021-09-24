package com.reins.bookstore.service;

import com.reins.bookstore.entity.CartResult;
import com.reins.bookstore.entity.OrderInfo;
import com.reins.bookstore.entity.OrderQueue;

import java.util.List;

public interface OrderService {
    void addOrder(OrderQueue order);

    List<CartResult> getOrder(Integer user_id);
}

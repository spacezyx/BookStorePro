package com.reins.bookstore.dao;

import com.reins.bookstore.entity.BookItem;
import com.reins.bookstore.entity.CartResult;
import com.reins.bookstore.entity.OrderInfo;

import java.util.List;

public interface OrderDao {
    void addOrder(Integer user_id,List<BookItem> bookItems);

    List<CartResult> getOrder(Integer user_id);
}

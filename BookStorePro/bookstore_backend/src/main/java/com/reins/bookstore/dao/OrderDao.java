package com.reins.bookstore.dao;

import com.reins.bookstore.entity.CartResult;

import java.util.List;

public interface OrderDao {
    void addOrder(Integer user_id);
}

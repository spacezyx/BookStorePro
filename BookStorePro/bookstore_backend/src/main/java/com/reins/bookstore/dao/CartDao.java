package com.reins.bookstore.dao;

import com.reins.bookstore.entity.Cart;
import com.reins.bookstore.entity.CartResult;

import java.util.List;

public interface CartDao {
    List<CartResult> getCartsByUser_id(Integer user_id);

    void addCart(Integer user_id,Integer book_id,Integer num);
    void cleanCartByUser_Id(Integer user_id);
}

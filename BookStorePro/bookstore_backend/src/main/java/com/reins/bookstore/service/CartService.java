package com.reins.bookstore.service;

import com.reins.bookstore.entity.Cart;
import com.reins.bookstore.entity.CartResult;

import java.util.List;


public interface CartService {
    List<CartResult> getCartsByUser_id(Integer user_id);

    Integer addCart(Integer user_id,Integer book_id,Integer num);
}

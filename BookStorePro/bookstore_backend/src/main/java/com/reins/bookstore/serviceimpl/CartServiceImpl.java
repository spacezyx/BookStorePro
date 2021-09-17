package com.reins.bookstore.serviceimpl;

import com.reins.bookstore.dao.CartDao;
import com.reins.bookstore.entity.CartResult;
import com.reins.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;

    @Override
    public List<CartResult> getCartsByUser_id(Integer user_id) {
        return cartDao.getCartsByUser_id(user_id);
    }

    @Override
    public void addCart(Integer user_id,Integer book_id,Integer num){
        cartDao.addCart(user_id,book_id,num);
    }
}

package com.reins.bookstore.controller;

import com.reins.bookstore.entity.CartResult;
import com.reins.bookstore.service.CartService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @RequestMapping("/getCarts")
    public List<CartResult> getCartsByUser_id(@RequestBody Object params) {
        JSONObject jsonObject = JSONObject.fromObject(params);
        Integer user_id = jsonObject.getInt("user_id");
        System.out.println(user_id);
        return cartService.getCartsByUser_id(user_id);
    }

    @RequestMapping("/addCart")
    public void addCart(@RequestBody Object params) {
        JSONObject jsonObject = JSONObject.fromObject(params);
        Integer user_id = jsonObject.getInt("user_id");
        Integer book_id = jsonObject.getInt("book_id");
        Integer num = jsonObject.getInt("num");
        System.out.println(user_id);
        cartService.addCart(user_id,book_id,num);
    }

}

package com.reins.bookstore.controller;

import com.reins.bookstore.entity.CartResult;
import com.reins.bookstore.security.SecurityUtils;
import com.reins.bookstore.service.CartService;
import com.reins.bookstore.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;


//有状态服务
@RestController
@Scope("session")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    WebApplicationContext applicationContext;

    @Autowired
    private UserService userService;

    String username = SecurityUtils.getCurrentUsername();

    @RequestMapping("/getCarts")
    public List<CartResult> getCartsByUser_id() {
        Integer user_id = userService.getUserId(username);
        return cartService.getCartsByUser_id(user_id);
    }

    @RequestMapping("/addCart")
    public String addCart(@RequestBody Object params) {
        Integer user_id = userService.getUserId(username);
        JSONObject jsonObject = JSONObject.fromObject(params);
        Integer book_id = jsonObject.getInt("book_id");
        Integer num = jsonObject.getInt("num");
        System.out.println(user_id);
        CartService cart1 = applicationContext.getBean(CartService.class);
        System.out.println(cart1);
        System.out.println(this);
        Integer book_num = cart1.addCart(user_id,book_id,num);
        String res = "本次加购"+book_num.toString()+"本书。";
        return res;
    }

}

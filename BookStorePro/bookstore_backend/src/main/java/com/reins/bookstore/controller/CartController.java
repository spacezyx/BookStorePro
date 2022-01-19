package com.reins.bookstore.controller;

import com.reins.bookstore.entity.Book;
import com.reins.bookstore.entity.CartResult;
import com.reins.bookstore.security.SecurityUtils;
import com.reins.bookstore.service.BookService;
import com.reins.bookstore.service.CartService;
import com.reins.bookstore.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//有状态服务
@RestController
//@Scope("session")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private BookService bookService;

    @Autowired
    WebApplicationContext applicationContext;

    @Autowired
    private UserService userService;

    private StringRedisTemplate redisTemplate;

    @RequestMapping("/getCarts")
    public List<CartResult> getCartsByUser_id(HttpServletRequest request) {
        HttpSession session = request.getSession();
        //String sessionId = session.getId();
//        String username = (String) session.getAttribute("username");
//        String requestURI = request.getRequestURI();
//        Integer user_id = userService.getUserId(username);
        Object tmp =session.getAttribute("cart");
        System.out.println(tmp);

        JSONObject jsonobject = JSONObject.fromObject(tmp);
        System.out.println(jsonobject);

        Map<String, Integer> map = (Map<String, Integer>) jsonobject;
        if(map == null){
            return new ArrayList<>();
        }
        else {
            List<CartResult> res = new ArrayList<>();
            for (String key : map.keySet()) {
//                System.out.println(key + " ：" + map.get(key));
                Integer user_id = 0;
                Integer book_id = Integer.parseInt(key);
                Book book = bookService.findBookById(book_id);
                Integer num = map.get(key);
                CartResult t = new CartResult(user_id, book, num);
                res.add(t);
            }
            return res;
        }
    }

    @RequestMapping("/addCart")
    public String addCart(HttpServletRequest request, @RequestBody Object params) {
        String username = SecurityUtils.getCurrentUsername();
        Integer user_id = userService.getUserId(username);
        JSONObject jsonObject = JSONObject.fromObject(params);
        Integer book_id = jsonObject.getInt("book_id");
        Integer num = jsonObject.getInt("num");
        HttpSession session = request.getSession();
        Object tmp = session.getAttribute("cart");
        System.out.println(tmp);
        JSONObject jsonobject = JSONObject.fromObject(tmp);
        System.out.println(jsonobject);
        Map<String, Integer> map = (Map<String, Integer>) jsonobject;
        if(map.isEmpty()){
           map = new HashMap<>();
        }
        //判断购物车里面是否有该商品 ，如果有  数量 + 1  ，如果没有，数量-1
        if(map.containsKey(book_id.toString())){
            map.put(book_id.toString() , map.get(book_id) +num );
        }else{
            System.out.println(map);
            System.out.println(book_id);
            System.out.println(num);
            map.put(book_id.toString(), num );
            System.out.println(map);
        }
        String mytmp = map.toString();
        //3. 存储到session中。
        request.getSession().setAttribute("cart" , mytmp);
        System.out.println(session.getId());
        System.out.println(mytmp);
        redisTemplate.opsForValue().set(session.getId(), mytmp);
//        CartService cart1 = applicationContext.getBean(CartService.class);
//        System.out.println(cart1);
//        System.out.println(this);
//        Integer book_num = cart1.addCart(user_id,book_id,num);
//        String res = "本次加购"+book_num.toString()+"本书。";
//        cartService.addCart(user_id,book_id,num);
        return "success";
    }

}

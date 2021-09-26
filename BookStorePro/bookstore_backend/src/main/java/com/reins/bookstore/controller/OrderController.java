package com.reins.bookstore.controller;

import com.reins.bookstore.entity.BookItem;
import com.reins.bookstore.entity.CartResult;
import com.reins.bookstore.entity.OrderQueue;
import com.reins.bookstore.security.SecurityUtils;
import com.reins.bookstore.service.OrderService;
import com.reins.bookstore.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;


//JMS异步处理订单程序 controller A：反馈给用户订单已接收并将订单数据发送到OrderQueue
@RestController
@Scope("session")
public class OrderController {

    @Autowired
    WebApplicationContext applicationContext;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    String username = SecurityUtils.getCurrentUsername();

    @RequestMapping("/addOrder")
    public String addOrder(@RequestBody Object params) {
        JSONArray json = JSONArray.fromObject(params);
        System.out.println(json);
        List<CartResult> jsonObject= JSONArray.toList(json,new CartResult(),new JsonConfig());

        List<BookItem> res = new ArrayList<>();
        Integer size = jsonObject.size();
        for(int i = 0; i < size; i++){
            BookItem tmp = new BookItem();
            tmp.setBook_id(jsonObject.get(i).getBook().getBookId());
            tmp.setNum(jsonObject.get(i).getNum());
            res.add(tmp);
        }
        System.out.println(res);

        Integer user_id = userService.getUserId(username);
        JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);
        System.out.println("Sending an message to service");
        jmsTemplate.convertAndSend("order", new OrderQueue(user_id,res));
        System.out.println(user_id);
        return "订单已接收";
    }

    @RequestMapping("/getOrder")
    public List<CartResult> getOrder(@RequestBody Object params) {
        Integer user_id = userService.getUserId(username);
        return orderService.getOrder(user_id);
    }
}

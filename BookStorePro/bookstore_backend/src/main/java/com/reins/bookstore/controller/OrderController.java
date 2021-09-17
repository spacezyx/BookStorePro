package com.reins.bookstore.controller;

import com.reins.bookstore.serviceimpl.OrderServiceImpl;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

@RestController
public class OrderController {
    @Autowired
    WebApplicationContext applicationContext;

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @RequestMapping("/addOrder")
    public String addOrder(@RequestBody Object params) {
        JSONObject jsonObject = JSONObject.fromObject(params);
        Integer user_id = jsonObject.getInt("user_id");
//        JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);

        System.out.println("Sending an message to service");
//        jmsTemplate.convertAndSend("order","新增order");

        System.out.println(user_id);

        orderServiceImpl.addOrder(user_id);

        return "订单已接收";

    }

}

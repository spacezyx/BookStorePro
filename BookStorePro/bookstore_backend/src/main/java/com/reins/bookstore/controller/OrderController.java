package com.reins.bookstore.controller;

import com.reins.bookstore.entity.CartResult;
import com.reins.bookstore.entity.OrderInfo;
import com.reins.bookstore.entity.OrderQueue;
import com.reins.bookstore.security.SecurityUtils;
import com.reins.bookstore.service.OrderService;
import com.reins.bookstore.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import javax.jms.ConnectionFactory;
import java.io.Console;
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
        Integer user_id = userService.getUserId(username);
        JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);

        System.out.println("Sending an message to service");
        jmsTemplate.convertAndSend("order", new OrderQueue("order",user_id));

        System.out.println(user_id);

        return "订单已接收";
    }

    @RequestMapping("/getOrder")
    public List<CartResult> getOrder(@RequestBody Object params) {
        Integer user_id = userService.getUserId(username);
        return orderService.getOrder(user_id);
    }
}

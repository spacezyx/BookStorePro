package com.reins.bookstore.controller;

import com.reins.bookstore.serviceimpl.OrderServiceImpl;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
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

@RestController
@EnableJms
public class OrderController {

    @Bean
    public JmsListenerContainerFactory<?> myFactory(@Qualifier("jmsConnectionFactory") ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // This provides all boot's default to this factory, including the message converter
        configurer.configure(factory, connectionFactory);
        // You could still override some of Boot's default if necessary.
        return factory;
    }

    @Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    @Autowired
    WebApplicationContext applicationContext;

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @RequestMapping("/addOrder")
    public String addOrder(@RequestBody Object params) {
        JSONObject jsonObject = JSONObject.fromObject(params);
        Integer user_id = jsonObject.getInt("user_id");
        JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);

        System.out.println("Sending an message to service");
        //jmsTemplate.convertAndSend("order", new CartResult());
        jmsTemplate.convertAndSend("mailbox", new OrderQueue("info@example.com", "Hello Msg"));

        System.out.println(user_id);

        orderServiceImpl.addOrder(user_id);

        return "订单已接收";

    }

}

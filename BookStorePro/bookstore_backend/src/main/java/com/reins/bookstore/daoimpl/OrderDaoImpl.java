package com.reins.bookstore.daoimpl;

import com.reins.bookstore.dao.OrderDao;
import com.reins.bookstore.entity.Cart;
import com.reins.bookstore.entity.Order;
import com.reins.bookstore.repository.CartRepository;
import com.reins.bookstore.repository.OrderInfoRepository;
import com.reins.bookstore.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    OrderInfoRepository orderInfoRepository;

    @Override
    public void addOrder(Integer user_id){
        orderRepository.addOrder(user_id);
        Integer order_id = orderRepository.last_insert_id();
        System.out.println("最新主键："+order_id);

        List<Cart> re = cartRepository.getCarts(user_id);
        int size = re.size();
        System.out.println(size);


        for(int i=0;i<size;i++){
            Integer book_id = re.get(i).getBook_id();
            Integer num = re.get(i).getNum();
            orderInfoRepository.addOrderInfo(book_id,order_id,num);
        }
    }
}

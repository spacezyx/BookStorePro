package com.reins.bookstore.daoimpl;

import com.reins.bookstore.dao.OrderDao;
import com.reins.bookstore.entity.*;
import com.reins.bookstore.repository.BookRepository;
import com.reins.bookstore.repository.CartRepository;
import com.reins.bookstore.repository.OrderInfoRepository;
import com.reins.bookstore.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    OrderInfoRepository orderInfoRepository;

    @Autowired
    private BookRepository bookRepository;


    @Override
    public Integer addOrder(Integer user_id){
        orderRepository.addOrder(user_id);
        Integer order_id = orderRepository.last_insert_id();
        System.out.println("最新主键："+order_id);
        return order_id;
    }

    @Override
    public void addOrderInfo(Integer order_id,Integer book_id,Integer num){
        orderInfoRepository.addOrderInfo(book_id,order_id,num);
    }

    @Override
    public List<CartResult> getOrder(Integer user_id){
        List<Order> IDs = orderRepository.getOrderID(user_id);
        System.out.println(IDs);
        int size = IDs.size();
        List<OrderInfo> re = new ArrayList<>();
        List<OrderInfo> tmp = new ArrayList<>();
        for(int i=0;i<size;i++){
            Integer tmpid = IDs.get(i).getId();
            tmp = orderInfoRepository.getOrder(tmpid);
            int tmpsize = tmp.size();
            for(int j=0;j<tmpsize;j++){
                re.add(tmp.get(j));
                System.out.println("tmp is "+tmp.get(j));
            }
        }
        List<CartResult> result= new ArrayList<>();
        for(int i=0;i<re.size();i++){
            Integer t = re.get(i).getBook_id();
            Integer num = re.get(i).getNum();
            Book b = bookRepository.getOne(t);
            CartResult c = new CartResult(user_id,b,num);
            result.add(c);
        }
        return result;
    }
}

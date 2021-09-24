package com.reins.bookstore.repository;

import com.reins.bookstore.entity.Order;
import com.reins.bookstore.entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    @Transactional
    @Modifying
    @Query(value = "insert into bookstore.order(user_id) values (?1)", nativeQuery = true)
    void addOrder(@Param("user_id") Integer user_id);

    @Query(value = "select last_insert_id()",nativeQuery = true)
    Integer last_insert_id();

    @Query(value = "select * from bookstore.order where user_id = ?1", nativeQuery = true)
    List<Order> getOrderID(@Param("user_id") Integer user_id);
}

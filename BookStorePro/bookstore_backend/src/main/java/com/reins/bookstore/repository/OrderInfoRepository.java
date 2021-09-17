package com.reins.bookstore.repository;

import com.reins.bookstore.entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface OrderInfoRepository extends JpaRepository<OrderInfo,Integer> {
    @Transactional
    @Modifying
    @Query(value = "insert into order_info (book_id,order_id,num) values(?1, ?2, ?3)", nativeQuery = true)
    void addOrderInfo( @Param("book_id") Integer book_id, @Param("order_id") Integer order_id,@Param("num") Integer num);

}

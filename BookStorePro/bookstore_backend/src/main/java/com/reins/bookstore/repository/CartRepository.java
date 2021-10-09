package com.reins.bookstore.repository;

import com.reins.bookstore.entity.Book;
import com.reins.bookstore.entity.Cart;
import com.reins.bookstore.entity.CartResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Integer>{
    @Query(value = "select c from Cart c where user_id = :user_id")
    List<Cart> getCarts(@Param("user_id") Integer user_id);

    @Transactional
    @Modifying
    @Query(value = "insert into cart (user_id,book_id,num) values(?1, ?2, ?3)", nativeQuery = true)
    void addCart(@Param("user_id") Integer user_id,@Param("book_id") Integer book_id,@Param("num") Integer num);

    @Transactional
    @Modifying
    @Query(value = "DELETE from Cart where book_id = :book_id")
    void cleanCartByBook_Id(@Param("book_id") Integer book_id);

}
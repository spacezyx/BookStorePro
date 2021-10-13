package com.reins.bookstore.entity;

import lombok.Data;


@Data
public class CartResult {
    private Integer user_id;
    private Book book;
    private Integer num;

    public CartResult(){
        super();
    }
    public  CartResult(Integer user_id,Book book,Integer num){
        this.user_id = user_id;
        this.book = book;
        this.num = num;

    }

}

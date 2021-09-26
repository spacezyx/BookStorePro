package com.reins.bookstore.entity;

import lombok.Data;

@Data
public class BookItem {
    private Integer book_id;
    private Integer num;

    public BookItem(){
        super();
    }
    public  BookItem(Integer book_id,Integer num){
        this.book_id = book_id;
        this.num = num;

    }
}

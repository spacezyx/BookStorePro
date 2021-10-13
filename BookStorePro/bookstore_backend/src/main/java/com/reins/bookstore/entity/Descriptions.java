package com.reins.bookstore.entity;

import lombok.Data;

@Data
public class Descriptions {
    private Integer id;
    private String descriptions;

    public Descriptions(){
        super();
    }
    public  Descriptions(Integer id,String descriptions){
        this.id = id;
        this.descriptions = descriptions;

    }
}

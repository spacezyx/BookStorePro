package com.reins.bookstore.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;

@Document(collection = "test")
public class Descriptions {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @Column(name = "id")
    private Integer id;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private String data;

    public  Descriptions(Integer id,String descriptions){
        this.id = id;
        this.data = descriptions;
    }

    public Descriptions(){}

}

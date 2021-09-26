package com.reins.bookstore.entity;

import org.springframework.context.annotation.Bean;

import java.util.List;


//OrderQueue 用于JMS异步处理订单程序
public class OrderQueue {

	private Integer user_id;
	private List<BookItem> bookItems;

	public OrderQueue() {
	}

	public OrderQueue(Integer user_id, List<BookItem> bookItems) {
		this.user_id = user_id;
		this.bookItems = bookItems;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public List<BookItem> getBookItems() {
		return bookItems;
	}

	public void setBookItems(List<BookItem> bookItems) {
		this.bookItems = bookItems;
	}

	@Override
	@Bean
	public String toString() {
		return String.format("user_id=%s, bookItems=%s", getUser_id(), getBookItems());
	}

}

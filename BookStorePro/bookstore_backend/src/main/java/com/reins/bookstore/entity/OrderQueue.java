package com.reins.bookstore.entity;

import org.springframework.context.annotation.Bean;

public class OrderQueue {

	private String to;
	private Integer body;

	public OrderQueue() {
	}

	public OrderQueue(String to, Integer body) {
		this.to = to;
		this.body = body;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Integer getBody() {
		return body;
	}

	public void setBody(Integer body) {
		this.body = body;
	}

	@Override
	@Bean
	public String toString() {
		return String.format("Email{to=%s, body=%s}", getTo(), getBody());
	}

}

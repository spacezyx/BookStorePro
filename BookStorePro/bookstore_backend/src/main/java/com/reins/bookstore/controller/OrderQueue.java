package com.reins.bookstore.controller;

import org.springframework.context.annotation.Bean;

public class OrderQueue {

	private String to;
	private String body;

	public OrderQueue() {
	}

	public OrderQueue(String to, String body) {
		this.to = to;
		this.body = body;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	@Bean
	public String toString() {
		return String.format("Email{to=%s, body=%s}", getTo(), getBody());
	}

}

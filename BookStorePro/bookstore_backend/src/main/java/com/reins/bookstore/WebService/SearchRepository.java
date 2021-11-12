package com.reins.bookstore.WebService;

import io.spring.guides.gs_producing_web_service.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchRepository {
	public List<Book> initData(List<com.reins.bookstore.entity.Book> book) {
		List<Book> res = new ArrayList<>();
		int size = book.size();
		for(int i = 0; i < size; i++){
			Book result = new Book();
			result.setId(book.get(i).getBookId());
			result.setIsbn(book.get(i).getIsbn());
			result.setName(book.get(i).getName());
			result.setType(book.get(i).getType());
			result.setAuthor(book.get(i).getAuthor());
			result.setPrice(book.get(i).getPrice());
			result.setDescription(book.get(i).getDescription());
			result.setInventory(book.get(i).getInventory());
			result.setImage(book.get(i).getBookImage().getImageFile());
			res.add(result);
		}
		return res;
	}

}

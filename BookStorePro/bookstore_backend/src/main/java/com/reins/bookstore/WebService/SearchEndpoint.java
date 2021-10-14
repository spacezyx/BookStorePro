package com.reins.bookstore.WebService;

//import com.reins.bookstore.entity.Book;
import com.reins.bookstore.service.BookService;
import io.spring.guides.gs_producing_web_service.Book;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.guides.gs_producing_web_service.GetBookRequest;
import io.spring.guides.gs_producing_web_service.GetBookResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Endpoint
public class SearchEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	private BookService bookService;

	@Autowired
	public SearchEndpoint(BookService bookService) {
		this.bookService = bookService;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookRequest")
	@ResponsePayload
	public GetBookResponse getBook(@RequestPayload GetBookRequest request) throws IOException, ParseException {
        GetBookResponse response = new GetBookResponse();
		List<com.reins.bookstore.entity.Book> list;
		list = bookService.searchDescriptions(request.getText());
		int size = list.size();
		for(int i = 0; i < size; i++){
			Book book = null;
			BeanUtils.copyProperties(list.get(i), book);
			response.setBook(book);
		}
		return response;
	}
}

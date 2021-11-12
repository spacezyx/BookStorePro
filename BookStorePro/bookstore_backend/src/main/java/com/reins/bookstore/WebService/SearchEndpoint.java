package com.reins.bookstore.WebService;

//import com.reins.bookstore.entity.Book;
import com.reins.bookstore.service.BookService;
import io.spring.guides.gs_producing_web_service.Book;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.guides.gs_producing_web_service.GetBookRequest;
import io.spring.guides.gs_producing_web_service.GetBookResponse;

import java.io.IOException;
import java.util.List;

@Endpoint
public class SearchEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	private BookService bookService;
	private SearchRepository searchRepository;

	@Autowired
	public SearchEndpoint(BookService bookService, SearchRepository searchRepository) {
		this.bookService = bookService;
		this.searchRepository = searchRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookRequest")
	@ResponsePayload
	public GetBookResponse getBook(@RequestPayload GetBookRequest request) throws IOException, ParseException {
        GetBookResponse response = new GetBookResponse();
		List<com.reins.bookstore.entity.Book> list;
		list = bookService.searchDescriptions(request.getText());
		List<Book> li = searchRepository.initData(list);

		int size = li.size();

		for(int i = 0; i < size; i++){
			response.setBook(li.get(i));
		}
		System.out.println("response is "+response);
		return response;
	}
}

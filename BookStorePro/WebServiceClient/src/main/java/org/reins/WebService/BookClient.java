
package org.reins.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import org.reins.wssample.wsdl.GetBookRequest;
import org.reins.wssample.wsdl.GetBookResponse;

public class BookClient extends WebServiceGatewaySupport {

	private static final Logger log = LoggerFactory.getLogger(BookClient.class);

	public GetBookResponse getBook(String text) {

		GetBookRequest request = new GetBookRequest();
		request.setText(text);


		log.info("Requesting location for " + text);

		GetBookResponse response = (GetBookResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8080/ws/search", request,
						new SoapActionCallback(
								"http://spring.io/guides/gs-producing-web-service/GetBookRequest"));
		return response;
	}

}

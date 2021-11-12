
package org.reins.WebService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.reins.wssample.wsdl.GetBookResponse;

@SpringBootApplication
public class WebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner lookup(BookClient quoteClient) {
		return args -> {
			String text = "three-body";

			if (args.length > 0) {
				text = args[0];
			}
			GetBookResponse response = quoteClient.getBook(text);
			System.err.println(response.getBook());

		};
	}

}

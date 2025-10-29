package com.southdragon.book_ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookEcommerceApplication.class, args);
	}

}

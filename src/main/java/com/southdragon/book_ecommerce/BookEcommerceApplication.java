package com.southdragon.book_ecommerce;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;

@SpringBootApplication
@EnableJpaAuditing
@Slf4j
public class BookEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookEcommerceApplication.class, args);
	}
    @PostConstruct
    public void init(){
        // Setting Spring Boot SetTimeZone
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        ZoneId zone = ZoneId.of("Asia/Ho_Chi_Minh");
        ZonedDateTime nowAtZone = ZonedDateTime.now(zone);
        log.info("ZonedDateTime - Now at Zone Asia/Ho_Chi_Minh : {}", nowAtZone);
    }
}

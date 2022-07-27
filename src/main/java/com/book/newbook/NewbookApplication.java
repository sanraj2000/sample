package com.book.newbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@SpringBootApplication
public class NewbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewbookApplication.class, args);
	}

}

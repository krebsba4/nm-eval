package com.nm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NmEvalServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NmEvalServiceApplication.class, args);
	}
}

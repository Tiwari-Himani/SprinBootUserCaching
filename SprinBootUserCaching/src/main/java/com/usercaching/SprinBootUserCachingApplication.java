package com.usercaching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SprinBootUserCachingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprinBootUserCachingApplication.class, args);
	}

}

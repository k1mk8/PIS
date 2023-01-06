package com.pisproject.lawtextdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={MongoReactiveAutoConfiguration.class, MongoReactiveDataAutoConfiguration.class, SecurityAutoConfiguration.class})
public class LawTextDBApplication {
	public static void main(String[] args) {
		SpringApplication.run(LawTextDBApplication.class, args);
	}
}

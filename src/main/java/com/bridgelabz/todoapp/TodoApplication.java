package com.bridgelabz.todoapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bridgelabz.todoapp.utilservice.interceptor.TokenParseInterceptor;

/**
 * @author bridgelabz
 * @since 12/7/2018 <br>
 *        <p>
 *        entity from which the spring boot application starts
 *        </p>
 */
@SpringBootApplication
public class TodoApplication {
	public static final Logger logger = LoggerFactory.getLogger(TodoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
		logger.info("application started");
	}
}

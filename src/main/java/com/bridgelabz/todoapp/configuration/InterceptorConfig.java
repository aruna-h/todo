package com.bridgelabz.todoapp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bridgelabz.todoapp.utilservice.interceptor.TokenParseInterceptor;
/**
 * @author bridgelabz
 * @since 27/7/2018 <br>
 *        <p>
 *        an entity providing configuration for Interceptor 
 *        </p>
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	@Autowired
	TokenParseInterceptor tokenparseinterceptor;

	public void addInterceptors(InterceptorRegistry registry) {
		 registry.addInterceptor(new TokenParseInterceptor()).addPathPatterns("/note/**");
	}
}

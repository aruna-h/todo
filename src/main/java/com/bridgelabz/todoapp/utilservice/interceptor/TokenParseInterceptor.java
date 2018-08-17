package com.bridgelabz.todoapp.utilservice.interceptor;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.bridgelabz.todoapp.utilservice.Utility;
import com.bridgelabz.todoapp.utilservice.redisrepository.RedisRepository;

import io.jsonwebtoken.Claims;

/**
 * @author bridgelabz
 * @since 27/7/2018 <br>
 *        <p>
 *        an interceptor for parsing token
 *        </p>
 */
@Component
public class TokenParseInterceptor implements HandlerInterceptor {
	//@Autowired
	//RedisRepository redisRepository;
	public static final Logger logger = LoggerFactory.getLogger(TokenParseInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		logger.info("IN INTERCEPTOR");
		String token = request.getHeader("token");
		logger.info(token);
		Utility utility=new Utility();
		Claims userId = utility.parseJwt(token);
		logger.info(userId.getId());
		
		//String redisToken=(String) redisRepository.getFromRedis(userId.getId());//token from Redis
		//logger.info(redisToken);
		
		//if(redisToken!=null)
		//{
		request.setAttribute("userId", userId.getId());
		return true;
		//}
		//return false;
	}

}

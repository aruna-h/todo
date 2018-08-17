package com.bridgelabz.todoapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import com.bridgelabz.todoapp.userservice.model.User;

/**
 * @author bridgelabz
 * @since 27/07/2018 <br>
 *        <p>
 *        Entity giving information about the Redis configuration <br>
 *        </p>
 */
@Configuration
@ComponentScan("com.bridgelabz.todoapp")
public class RedisConfig {
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}

	@Bean
	public RedisTemplate<String, User> redisTemplate() {
		final RedisTemplate<String, User> template = new RedisTemplate<String, User>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}
}

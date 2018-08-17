package com.bridgelabz.todoapp.configuration;

import org.modelmapper.ModelMapper;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
/*import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
*/
/**
 * @author bridgelabz
 * @since 18/07/2018 <br>
 *        <p>
 *        Entity of mapping configuration 
 *       <br>
 *        </p>
 */
@Configuration
@ComponentScan("com.bridgelabz.todoapp")
public class ToDoMapConfig {

	@Bean
	public ModelMapper model() {
		return new ModelMapper();
	}
	/*@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		return template;
	}*/
}

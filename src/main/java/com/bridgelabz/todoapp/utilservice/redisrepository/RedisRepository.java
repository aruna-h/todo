package com.bridgelabz.todoapp.utilservice.redisrepository;

import io.jsonwebtoken.Claims;

/**
 * @author bridgelabz
 * @since 18/07/2018 <br>
 *        <p>
 *        Business Entity of redisrepository <br>
 *        </p>
 */
public interface RedisRepository {

	void saveInRedis(String token);

	String getFromRedis(String userId);


}

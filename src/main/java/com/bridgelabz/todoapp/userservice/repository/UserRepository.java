package com.bridgelabz.todoapp.userservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import com.bridgelabz.todoapp.userservice.model.User;

import io.jsonwebtoken.Claims;

/**
 * @author bridgelabz
 * @since 12/7/2018 <br>
 *        <p>
 *        providing userRepository interface
 *        </p>
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

	/**
	 * method declaration for finding the user by email
	 * 
	 * @param string
	 * @return get user by email
	 */
	public Optional<User> findByEmail(String string);

	/**
	 * method declaration to save information of the user
	 * 
	 * @param user
	 */
	public void save(Optional<User> user);

	/**
	 * method declaration for finding the user by id
	 * 
	 * @param email
	 * @return
	 */
	public Optional<User> findById(String string);

}

package com.bridgelabz.todoapp.userservice.service;

import javax.mail.MessagingException;




import com.bridgelabz.todoapp.exceptionservice.ToDoException;
import com.bridgelabz.todoapp.userservice.dto.ResetPasswordDTO;
import com.bridgelabz.todoapp.userservice.dto.UserDto;

/**
 * @author bridgelabz

 * @since 12/7/2018 <br>
 *        <p>
 *        an interface for userService class
 *        </p>
 */
public interface IUserService {
	/**
	 * Register the user Details in todo application
	 * 
	 * @param registerDTO
	 *            {@link RegistrationDTO} registerDTO is reference variable it has
	 *            registered user data
	 * @return 
	 * @throws RegistrationException
	 * @throws MessagingException
	 * @throws UserActivationException
	 */
	public String registerUser(UserDto userDto, String uri) throws ToDoException, MessagingException;
	/**
	 * login the user in application if the user is present
	 * 
	 * @param user
	 *            user is reference variable
	 * @return 
	 * @throws LoginException
	 * @throws MessagingException
	 * @throws UserActivationException
	 * @throws ToDoException 
	 */
	public String loginUser(UserDto userDto, String uri) throws ToDoException, MessagingException;

	/**
	 * @param token
	 * @throws UserActivationException
	 */
	public void setActivationStatus(String token) throws ToDoException;

	/**
	 * @param resetPasswordDTO
	 * @param token
	 * @throws UserActivationException
	 * @throws RegistrationException
	 */
	public void resetPassword(ResetPasswordDTO resetPasswordDTO, String token)
			throws ToDoException;

	/**
	 * @param emailId
	 * @param uri
	 * @throws RegistrationException
	 * @throws MessagingException
	 */
	public void forgotPassword(String emailId, String uri) throws ToDoException, MessagingException;
	

	

}
package com.bridgelabz.todoapp.userservice.service;

import java.util.Optional;

import javax.mail.MessagingException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.todoapp.exceptionservice.ToDoException;
import com.bridgelabz.todoapp.securityservice.UserEmailSecurity;
import com.bridgelabz.todoapp.userservice.controller.UserController;
import com.bridgelabz.todoapp.userservice.dto.MailDTO;
import com.bridgelabz.todoapp.userservice.dto.ResetPasswordDTO;
import com.bridgelabz.todoapp.userservice.dto.UserDto;
import com.bridgelabz.todoapp.userservice.model.User;
import com.bridgelabz.todoapp.userservice.repository.UserRepository;
import com.bridgelabz.todoapp.utilservice.PreConditions;
import com.bridgelabz.todoapp.utilservice.Utility;
import com.bridgelabz.todoapp.utilservice.messageAccessor.Messages;
import com.bridgelabz.todoapp.utilservice.redisrepository.RedisRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import io.jsonwebtoken.Claims;

/**
 * @author bridgelabz
 * @since 12/7/2018 <br>
 *        <p>
 *        business entity having implementation of IUserService info
 *        </p>
 */
@Service
public class UserService implements IUserService {
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private UserEmailSecurity userEmailSecurity;
	@Autowired
	ModelMapper objectmapper;
	@Autowired
	RedisRepository redisRepository;
	@Autowired
	Messages messages;
	@Autowired
	Utility utility;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bridgelabz.todoapp.userservice.service.IUserService#registerUser(com.
	 * bridgelabz.todoapp.userservice.dto.UserDto, java.lang.String)
	 */
	@Override
	public String registerUser(UserDto userDto, String uri) throws ToDoException, MessagingException {
		logger.info("user registering");
		PreConditions.checkNotNull(userDto.getEmail(), messages.get("1"));
		PreConditions.checkNotNull(userDto.getFirstName(), messages.get("2"));
		PreConditions.checkNotNull(userDto.getLastName(), messages.get("3"));
		PreConditions.checkNotNull(userDto.getId(), messages.get("4"));
		PreConditions.checkNotNull(userDto.getMobNumber(), messages.get("5"));
		PreConditions.checkNotNull(userDto.getUserName(), messages.get("6"));
		PreConditions.checkNotNull(userDto.getPassword(), messages.get("7"));
		Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

		Utility.isValidateAllFields(userDto);

		User userMap = objectmapper.map(userDto, User.class);
		userMap.setPassword(encoder.encode(userDto.getPassword()));
		userRepository.save(userMap);

		Optional<User> optionalUser1 = userRepository.findByEmail(userDto.getEmail());
		sendEmailMessage(uri, optionalUser1);
		String token = utility.createToken(userDto);
		logger.info("user registered successfully");
		return token;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bridgelabz.todoapp.userservice.service.IUserService#loginUser(com.
	 * bridgelabz.todoapp.userservice.dto.UserDto, java.lang.String)
	 */
	@Override
	public String loginUser(UserDto userDto, String uri) throws ToDoException, MessagingException {
		logger.info("login  User");
		PreConditions.checkNotNull(userDto.getEmail(), messages.get("1"));
		PreConditions.checkNotNull(userDto.getPassword(), messages.get("7"));
		Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
		
		if (!optionalUser.isPresent()) {
			throw new ToDoException(messages.get("16"));
		}
		if (!encoder.matches(userDto.getPassword(), optionalUser.get().getPassword())) {
			throw new ToDoException(messages.get("17"));
		}
		sendEmailMessage(uri, optionalUser);
		
		String token = utility.createToken(userDto);
		logger.info("login successfull");
		return token;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bridgelabz.todoapp.userservice.service.IUserService#forgotPassword(java.
	 * lang.String, java.lang.String)
	 */
	@Override
	public void forgotPassword(String email, String uri) throws ToDoException, MessagingException {
		logger.info("forgot password");
		PreConditions.checkNotNull(email, messages.get("1"));
		Optional<User> optionalUser = userRepository.findByEmail(email);
		if (!optionalUser.isPresent()) {
			throw new ToDoException(messages.get("18"));
		}
		sendEmailMessage(uri, optionalUser);
		logger.info(messages.get("19"));
	}

	/**
	 * @param uri
	 * @param optionalUser
	 * @throws ToDoException
	 * @throws MessagingException
	 */
	@SuppressWarnings("unchecked")
	public void sendEmailMessage(String uri, Optional<User> optionalUser) throws ToDoException, MessagingException {

		String token = Utility.createToken(optionalUser.get().getId());
		redisRepository.saveInRedis(token);
		
		MailDTO mailDTO = new MailDTO();
		mailDTO.setId(optionalUser.get().getId());
		mailDTO.setToMailAddress(optionalUser.get().getEmail());
		mailDTO.setSubject(" Verification mail");
		mailDTO.setSalutation("Hi " + optionalUser.get().getFirstName());
		mailDTO.setBody("To Activate your account click on this link: http://localhost:8080" + uri + "?token=" + token);
		mailDTO.setMailSign("\nThank you \n aruna");
	
		userEmailSecurity.sendEmail(mailDTO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bridgelabz.todoapp.userservice.service.IUserService#setActivationStatus(
	 * java.lang.String)
	 */
	@Override
	public void setActivationStatus(String token) throws ToDoException {
		logger.info("check the user activation");
		Claims claim = utility.parseJwt(token);

		Optional<User> optionalUser = userRepository.findById(claim.getId());

		if (!optionalUser.isPresent()) {
			throw new ToDoException(messages.get("18"));
		}
		User user = optionalUser.get();
		user.setIsActivate(true);
		userRepository.save(user);
		logger.info("User activated successfully");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bridgelabz.todoapp.userservice.service.IUserService#resetPassword(com.
	 * bridgelabz.todoapp.userservice.dto.ResetPasswordDTO, java.lang.String)
	 */
	@Override
	public void resetPassword(ResetPasswordDTO resetPasswordDTO, String token) throws ToDoException {
		logger.info("resetting password");
		PreConditions.checkNotNull(resetPasswordDTO.getNewPassword(), messages.get("8"));
		PreConditions.checkNotNull(resetPasswordDTO.getConfirmPassword(), messages.get("9"));
		Claims claim = utility.parseJwt(token);

		if (!Utility.validatePassword(resetPasswordDTO.getNewPassword())) {
			throw new ToDoException(messages.get("20"));
		}
		if (!Utility.isPasswordMatch(resetPasswordDTO.getNewPassword(), resetPasswordDTO.getConfirmPassword())) {
			throw new ToDoException(messages.get("21"));
		}

		Optional<User> optionalUser = userRepository.findById(claim.getId());

		if (!optionalUser.isPresent()) {
			throw new ToDoException(messages.get("22"));
		}
		User user = optionalUser.get();
		user.setPassword(encoder.encode(resetPasswordDTO.getNewPassword()));
		userRepository.save(user);
		logger.info("Reset password done successfully");
	}
}
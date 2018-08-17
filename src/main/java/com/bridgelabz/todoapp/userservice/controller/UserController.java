package com.bridgelabz.todoapp.userservice.controller;

import javax.mail.MessagingException;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.todoapp.exceptionservice.ToDoException;
import com.bridgelabz.todoapp.userservice.dto.ResetPasswordDTO;
import com.bridgelabz.todoapp.userservice.dto.ResponseDTO;
import com.bridgelabz.todoapp.userservice.dto.UserDto;
import com.bridgelabz.todoapp.userservice.service.IUserService;

/**
 * @author bridgelabz
 * @since 12/7/2018 <br>
 *        <p>
 *        a entity controlling login and registration and forgot password to a
 *        user
 *        </p>
 */
@RestController
@RequestMapping("/user")
public class UserController {
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	String REQ_ID = "IN_USER";
	String RES_ID = "OUT_USER";
	@Autowired
	private IUserService userService;

	ResponseDTO response = new ResponseDTO();

	/**
	 * @param request
	 * @return
	 * @throws ToDoException
	 * @throws MessagingException
	 */
	@PostMapping("/register")
	public ResponseEntity<ResponseDTO> registerUser(@Valid @RequestBody UserDto userDto, HttpServletRequest request)
			throws ToDoException, MessagingException {
		logger.info(REQ_ID);
		String token=userService.registerUser(userDto, request.getRequestURI());
		response.setMessage("Registered successfullyand Token is :"+token);
		response.setStatus(2);
		logger.info(RES_ID);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	/**
	 * @param loginDTO
	 * @param request
	 * @return
	 * @throws ToDoException
	 * @throws MessagingException
	 */
	@PostMapping("/login")
	public ResponseEntity<ResponseDTO> loginUser(@Valid @RequestBody UserDto userDto, HttpServletRequest request)
			throws ToDoException, MessagingException {
		logger.info(REQ_ID);
		String token = userService.loginUser(userDto, request.getRequestURI());
		logger.info(request.getRequestURI());
		response.setMessage("Login successfully and Token is : "+token );
		response.setStatus(3);
		logger.info(RES_ID);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * @param token
	 * @return
	 * @throws ToDoException
	 */
	@GetMapping("/activation")
	public ResponseEntity<ResponseDTO> activationUser(@RequestParam("token") String token) throws ToDoException {
		logger.info(REQ_ID);
		userService.setActivationStatus(token);
		response.setMessage("User activated successfully");
		logger.info(RES_ID);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * @param emailId
	 * @param request
	 * @return
	 * @throws ToDoException
	 * @throws MessagingException
	 */
	@PostMapping("/forgotPassword")
	public ResponseEntity<ResponseDTO> forgotPassword(@Valid @RequestParam String emailId, HttpServletRequest request)
			throws ToDoException, MessagingException {
		logger.info(REQ_ID);
		userService.forgotPassword(emailId, request.getRequestURI());
		response.setMessage("sent to user mailid to reset password");
		logger.info(RES_ID);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * @param resetPasswordDTO
	 * @param token
	 * @return
	 * @throws ToDoException
	 */
	@PostMapping("/resetPassword")
	public ResponseEntity<ResponseDTO> resetPassword(@Valid @RequestBody ResetPasswordDTO resetPasswordDTO,
			@RequestParam("token") String token) throws ToDoException {
		logger.info(REQ_ID);
		userService.resetPassword(resetPasswordDTO, token);
		response.setMessage("resetting the password successfully");
		logger.info(RES_ID);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}

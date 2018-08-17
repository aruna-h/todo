package com.bridgelabz.todoapp.utilservice;

import java.util.Date;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.bridgelabz.todoapp.exceptionservice.ToDoException;
import com.bridgelabz.todoapp.userservice.dto.UserDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author bridgelabz
 * @since 12/7/2018 <br>
 *        <p>
 *        a util entity providing common using methods
 *        </p>
 */
@Component
public class Utility {

	public Utility() {

	}

	/**
	 * function to validate all fields of user
	 * 
	 * @param userDto
	 */
	public static void isValidateAllFields(UserDto userDto) throws ToDoException {

		if (!validateEmailAddress(userDto.getEmail())) {
			throw new ToDoException("emailid not valid  Exception");
		} else if (!isValidUserName(userDto.getUserName())) {
			throw new ToDoException("UserName Not valid  Exception");
		} else if (!validatePassword(userDto.getPassword())) {
			throw new ToDoException("password not valid Exception");
		} else if (!isValidMobileNumber(userDto.getMobNumber())) {
			throw new ToDoException("mobilenumber not valid  Exception");
		} else if (!isPasswordMatch(userDto.getPassword(), userDto.getPassword())) {
			throw new ToDoException("password mismatch exception");
		}
	}

	/**
	 * function to validate email id
	 * 
	 * @param emailId
	 * @return
	 */
	public static boolean validateEmailAddress(String emailId) {
		Pattern emailNamePtrn = Pattern
				.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher mtch = emailNamePtrn.matcher(emailId);
		return mtch.matches();

	}

	/**
	 * function to validate password
	 * 
	 * @param password
	 * @return
	 */
	public static boolean validatePassword(String password) {
		Pattern pattern = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}");
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}

	/**
	 * function to validate username
	 * 
	 * @param userName
	 * @return
	 */
	public static boolean isValidUserName(String userName) {
		Pattern userNamePattern = Pattern.compile("^[a-z0-9_-]{3,10}$");
		Matcher matcher = userNamePattern.matcher(userName);
		return matcher.matches();

	}

	/**
	 * function to validate mobile number
	 * 
	 * @param mobileNumber
	 * @return
	 */
	public static boolean isValidMobileNumber(String mobileNumber) {
		Pattern mobileNumberPattern = Pattern.compile("\\d{10}");
		Matcher matcher = mobileNumberPattern.matcher(mobileNumber);
		return matcher.matches();
	}

	/**
	 * function to check whether password is matching or not
	 * 
	 * @param password
	 * @param confirmPassword
	 * @return
	 */
	public static boolean isPasswordMatch(String password, String confirmPassword) {
		return password.equals(confirmPassword);
	}

	/**
	 * function for creating token according to date
	 * 
	 * @param loginDTO
	 * @return
	 */
	public String createToken(UserDto UserDto) {
		final String key = "what";
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		String subject = UserDto.getEmail();
		Date date = new Date();

		JwtBuilder builder = Jwts.builder().setId(subject).setIssuedAt(date).signWith(signatureAlgorithm, key);
		return builder.compact();
	}

	/**
	 * function for creating token according to time
	 * 
	 * @param id
	 * @return
	 */
	public static String createToken(String id) {
		final String KEY = "what";
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		Date startTime = new Date();
		Date expireTime = new Date(startTime.getTime() + (1000 * 60 * 60 * 24));

		JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(startTime).setExpiration(expireTime)
				.signWith(signatureAlgorithm, KEY);
		return builder.compact();
	}

	/**
	 * function for parsing jwt
	 * 
	 * @param jwt
	 * @return
	 */
	public Claims parseJwt(String jwt) {
		final String KEY = "what";
		return Jwts.parser().setSigningKey(KEY).parseClaimsJws(jwt).getBody();
	}

	/**
	 * @param argument
	 * @param parameterName
	 * @return
	 */
	public static <T> T checkNotNull(T argument, String parameterName) {

		if (argument == null) {
			throw new IllegalArgumentException(parameterName + "\" cannot be null");
		}

		return argument;
	}

}
package com.bridgelabz.todoapp.securityservice;

import javax.mail.MessagingException;

import com.bridgelabz.todoapp.exceptionservice.ToDoException;
import com.bridgelabz.todoapp.userservice.dto.MailDTO;

/**
 * @author bridgelabz
 * @since 12/July/2018 <br>
 *        <p>
 *        interface of email security<br>
 *        </p>
 */
public interface UserEmailSecurity {
	public void sendEmail(MailDTO mailDTO) throws ToDoException, MessagingException;
}
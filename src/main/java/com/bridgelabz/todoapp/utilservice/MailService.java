package com.bridgelabz.todoapp.utilservice;

import javax.mail.MessagingException;

import org.springframework.stereotype.Service;

import com.bridgelabz.todoapp.utilservice.rabbitmq.Mail;

/**
 * @author bridgelabz
 * @since 19/07/2018 <br>
 *        <p>
 *        Business entity of mail service, which has method method declaration
 *        in it.<br>
 *        </p>
 */
@Service
public interface MailService {

	/**
	 * a function declaration of send mail.
	 * 
	 * @param mail
	 * @throws MessagingException
	 */
	public void sendMail(Mail mail) throws MessagingException;
}

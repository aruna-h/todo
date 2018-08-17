package com.bridgelabz.todoapp.securityservice;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.bridgelabz.todoapp.exceptionservice.ToDoException;
import com.bridgelabz.todoapp.userservice.dto.MailDTO;

/**
 * @author bridgelabz
 * @since 12/July/2018 <br>
 *        <p>
 *        entity providing implementation for email security interface<br>
 *        </p>
 */
@Component
public class UserEmailSecurityImpl implements UserEmailSecurity {
	@Autowired
	private JavaMailSender emailSender;

	@Override
	public void sendEmail(MailDTO mailDTO) throws ToDoException, MessagingException, MailException {

		MimeMessage mimeMessage = emailSender.createMimeMessage();
		MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

		message.setTo(mailDTO.getToMailAddress());
		message.setSubject(mailDTO.getSalutation() + "\n" + "\n" + mailDTO.getSubject());
		message.setText(mailDTO.getBody() + "\n" + "\n" + mailDTO.getMailSign());
		emailSender.send(mimeMessage);
	}
}
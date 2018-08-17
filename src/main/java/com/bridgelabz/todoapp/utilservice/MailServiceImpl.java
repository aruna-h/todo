package com.bridgelabz.todoapp.utilservice;

import javax.mail.MessagingException;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.bridgelabz.todoapp.utilservice.rabbitmq.Mail;

/**
 * @author bridgelabz
 * @since 19/07/2018 <br>
 *        <p>
 *        Entity giving implementation for the methods declared in mailService
 *        interface<br>
 *        </p>
 */
@Component
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendMail(Mail mail) throws MessagingException {

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setSubject(mail.getSubject());
		helper.setTo(mail.getTo());
		helper.setText(mail.getBody());

		javaMailSender.send(message);
	}
}

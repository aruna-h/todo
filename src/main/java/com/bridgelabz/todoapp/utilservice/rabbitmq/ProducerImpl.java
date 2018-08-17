package com.bridgelabz.todoapp.utilservice.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bridgelabz.todoapp.userservice.dto.MailDTO;

/**
 * @author bridgelabz
 * @since 19/07/2018 <br>
 *        <p>
 *        Entity giving implementation of producer and producer is the one who sends message.<br>
 *        </p>
 */
@Component
public class ProducerImpl implements IProducer {

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Value("${Todo.rabbitmq.exchange}")
	private String exchange;

	@Value("${Todo.rabbitmq.routingkey}")
	private String routingKey;

	public void produce(String to, String subject, String body) {
		MailDTO email = new MailDTO();
		email.setBody(body);
		email.setSubject(subject);
		email.setToMailAddress(to);;
		amqpTemplate.convertAndSend(exchange, routingKey, email);
	}

}
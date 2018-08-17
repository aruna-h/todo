package com.bridgelabz.todoapp.utilservice.rabbitmq;

import javax.mail.MessagingException;

/**
 * @author bridgelabz
 * @since 19/07/2018 <br>
 *        <p>
 *        Entity of consumer interface<br>
 *        </p>
 */
public interface IConsumer {
	/**
	 * a method declaration for consumer receiving message
	 * 
	 * @param email
	 * @throws MessagingException
	 */
	void recieve(Mail email) throws MessagingException;
}

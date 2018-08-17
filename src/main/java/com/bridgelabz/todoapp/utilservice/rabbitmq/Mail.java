package com.bridgelabz.todoapp.utilservice.rabbitmq;

/**
 * @author bridgelabz
 * @since 19/07/2018 <br>
 *        <p>
 *        Business entity containing information about Mail <br>
 *        </p>
 */
public class Mail {
	private String to;
	private String subject;
	private String body;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}

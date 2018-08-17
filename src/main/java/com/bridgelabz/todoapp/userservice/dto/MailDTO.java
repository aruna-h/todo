package com.bridgelabz.todoapp.userservice.dto;

/**
 * @author bridgelabz
 * @since 12/7/2018 <br>
 *        <p>
 *        a business entity giving info about mail DTO
 *        </p>
 */
public class MailDTO {
	private String id;

	private String toMailAddress;

	private String subject;

	private String salutation;

	private String body;

	private String mailSign;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getToMailAddress() {
		return toMailAddress;
	}

	public void setToMailAddress(String toMailAddress) {
		this.toMailAddress = toMailAddress;
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

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getMailSign() {
		return mailSign;
	}

	public void setMailSign(String mailSign) {
		this.mailSign = mailSign;
	}

	@Override
	public String toString() {
		return "MailDTO [id=" + id + ", toMailAddress=" + toMailAddress + ", subject=" + subject + ", salutation="
				+ salutation + ", body=" + body + ", mailSign=" + mailSign + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mailSign == null) ? 0 : mailSign.hashCode());
		result = prime * result + ((salutation == null) ? 0 : salutation.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((toMailAddress == null) ? 0 : toMailAddress.hashCode());
		return result;
	}
}

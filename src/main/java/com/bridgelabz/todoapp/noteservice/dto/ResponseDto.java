package com.bridgelabz.todoapp.noteservice.dto;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author bridgelabz
 * @since 18/7/2018 <br>
 *        <p>
 *        a business entity giving info about response dto
 *        </p>
 */
@SuppressWarnings("serial")
@Document
public class ResponseDto implements Serializable {
	@Id
	private int status;
	private String message;

	public ResponseDto() {

	}

	public ResponseDto(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int i) {
		this.status = i;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

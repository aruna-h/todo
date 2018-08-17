package com.bridgelabz.todoapp.exceptionservice;

/**
 * @author bridgelabz
 * @since 12/7/2018 <br>
 *        <p>
 *        entity to show exception
 *        </p>
 */
@SuppressWarnings("serial")
public class ToDoException extends Exception {
	String message;
	int code;

	public ToDoException(String message) {
		super(message);
	}

	public ToDoException(String message, int code) {
		this.message = message;
		this.code = code;
	}
}

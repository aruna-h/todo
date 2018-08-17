package com.bridgelabz.todoapp.exceptionservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestBody;

import com.bridgelabz.todoapp.noteservice.controller.NoteController;
import com.bridgelabz.todoapp.userservice.dto.ResponseDTO;

/**
 * @author bridgelabz
 * @since 12/7/2018 <br>
 *        <p>
 *        entity to handle exceptions
 *        </p>
 */
@ControllerAdvice
public class ExceptionHandler {
	public static final Logger logger = LoggerFactory.getLogger(NoteController.class);
	
	@org.springframework.web.bind.annotation.ExceptionHandler(ToDoException.class)
	public ResponseEntity<ResponseDTO> Exceptionhandler(@RequestBody ToDoException todoexception) {
		logger.info("Handling exception here");
		ResponseDTO response = new ResponseDTO();
		response.setMessage(todoexception.getMessage());
		response.setStatus(-1);

		return new ResponseEntity<ResponseDTO>(response, HttpStatus.BAD_REQUEST);
	}
}
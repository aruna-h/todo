package com.bridgelabz.todoapp.noteservice.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.todoapp.exceptionservice.ToDoException;
import com.bridgelabz.todoapp.noteservice.dto.LabelDto;
import com.bridgelabz.todoapp.noteservice.dto.NoteDto;
import com.bridgelabz.todoapp.noteservice.dto.ResponseDto;
import com.bridgelabz.todoapp.noteservice.model.Label;
import com.bridgelabz.todoapp.noteservice.model.Note;
import com.bridgelabz.todoapp.noteservice.service.INoteService;
import com.bridgelabz.todoapp.userservice.dto.ResponseDTO;

/**
 * @author bridgelabz
 * @since 18/07/2018 <br>
 *        <p>
 *        Entity giving info about note controller which controlling tasks such
 *        as creating,updating and deleting of a note.<br>
 *        </p>
 */
@RestController
@RequestMapping("/note")
public class NoteController {
	@Autowired
	INoteService inoteService;
	public static final Logger logger = LoggerFactory.getLogger(NoteController.class);
	static String REQ_ID = "IN_Note";
	static String RESP_ID = "OUT_Note";

	/**
	 * a response entity to create note.
	 * 
	 * @param note
	 * @param token
	 * @return
	 * @throws ToDoException
	 * @throws IOException 
	 */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/createnote", method = RequestMethod.POST)
	public ResponseEntity<String> createNote(@Valid @RequestBody NoteDto notedto,@RequestHeader("token") String token, HttpServletRequest request
			) throws ToDoException, IOException {
		String userId =  (String) request.getAttribute("userId");
		logger.info(REQ_ID + " " + userId);
		String noteId = inoteService.createNote(notedto, userId);
		logger.info(RESP_ID);
		return new ResponseEntity("Note successfully created with id : " + noteId, HttpStatus.OK);
	}

	/**
	 * a response entity to delete a note.
	 * 
	 * @param token
	 * @param noteId
	 * @return
	 * @throws ToDoException
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/deletenote/{noteId}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseDto> deleteNote(@Valid @PathVariable("noteId") String noteId,@RequestHeader("token") String token, HttpServletRequest request)
			throws ToDoException {
		String userId = (String) request.getAttribute("userId");
		logger.info(REQ_ID);
		inoteService.deleteNote(noteId, userId);
		logger.info(RESP_ID);
		return new ResponseEntity("Note Successfully deleted", HttpStatus.OK);
	}

	/**
	 * a response entity to update a note.
	 * 
	 * @param noteId
	 * @param title
	 * @param description
	 * @param token
	 * @return
	 * @throws ToDoException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/updatenote", method = RequestMethod.PUT)
	public ResponseEntity<ResponseDto> updateNote(@Valid @RequestParam String noteId, @RequestParam String title,
			@RequestParam String description,@RequestHeader("token") String token, HttpServletRequest request) throws ToDoException {
		String userId = (String) request.getAttribute("userId");
		logger.info(REQ_ID);
		inoteService.updateNote(noteId, title, description, userId);
		logger.info(RESP_ID);
		return new ResponseEntity("Note sucessfully updated", HttpStatus.OK);
	}

	/**
	 * a response entity to display all notes.
	 * 
	 * @param noteId
	 * @param token
	 * @return
	 * @throws ToDoException
	 */

	
	@RequestMapping(value = "/displayallNotes", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> displayAllNotes(@RequestHeader String token, HttpServletRequest request)
			throws ToDoException {
		String userId = (String) request.getAttribute("userId");
		logger.info(REQ_ID + " Displaying all notes");
		List<Note> list = null;
		@SuppressWarnings("unused")
		ResponseDTO response = new ResponseDTO();
	  inoteService.displayAllNotes(userId);
		logger.info(RESP_ID + "displayed");
		return new ResponseEntity(list, HttpStatus.OK);
	}

	/**
	 * method to delete note from trash
	 * 
	 * @param noteId
	 * @param token
	 * @return
	 * @throws ToDoException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/deletepermanently/{noteId}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseDTO> deleteFromTrash(@Valid @PathVariable("noteId") String noteId, @RequestHeader String token,
			HttpServletRequest request) throws ToDoException {
		String userId = (String) request.getAttribute("userId");
		logger.info(REQ_ID);
		inoteService.deleteFromTrash(noteId, userId);
		logger.info(RESP_ID);
		return new ResponseEntity("note successfully deleted", HttpStatus.OK);
	}

	/**
	 * method to restore note from trash
	 * 
	 * @param noteId
	 * @param token
	 * @return
	 * @throws ToDoException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/restoretrash/{noteId}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO> restoreFromTrash(@Valid @PathVariable("noteId") String noteId, @RequestHeader String token,
			HttpServletRequest request) throws ToDoException {
		String userId = (String) request.getAttribute("userId");
		logger.info(REQ_ID);
		inoteService.restoreFromTrash(noteId, userId);
		logger.info(RESP_ID);
		return new ResponseEntity("Restored from trash", HttpStatus.OK);
	}

	/**
	 * method to pin a note
	 * 
	 * @param noteId
	 * @param token
	 * @return
	 * @throws ToDoException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/ispin/{noteId}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO> pinnote(@Valid @PathVariable("noteId") String noteId, @RequestHeader String token,
			HttpServletRequest request) throws ToDoException {
		String userId = (String) request.getAttribute("userId");
		logger.info(REQ_ID);
		inoteService.pinnote(noteId, userId);
		logger.info(RESP_ID);
		return new ResponseEntity("pinned note", HttpStatus.OK);
	}

	/**
	 * method to archive a note
	 * 
	 * @param noteId
	 * @param token
	 * @return
	 * @throws ToDoException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/archive/{noteId}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO> archivenote(@PathVariable("noteId") String noteId, @RequestHeader String token,
			HttpServletRequest request) throws ToDoException {
		String userId = (String) request.getAttribute("userId");
		logger.info(REQ_ID);
		inoteService.archivenote(noteId, userId);
		logger.info(RESP_ID);
		return new ResponseEntity("archive note", HttpStatus.OK);

	}

	/**
	 * method to set a reminder to a note
	 * 
	 * @param noteId
	 * @param reminderTime
	 * @param token
	 * @return
	 * @throws ToDoException
	 * @throws ParseException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/reminder/{noteId}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO> setReminder(@PathVariable("noteId") String noteId, @RequestParam String reminderTime,
			@RequestHeader String token, HttpServletRequest request) throws ToDoException, ParseException {
		String userId = (String) request.getAttribute("userId");
		logger.info(REQ_ID);
		inoteService.setReminder(noteId, reminderTime, userId);
		logger.info("reminder ended");
		logger.info(RESP_ID);
		return new ResponseEntity("setting reminder", HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getdescription", method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO> getMetaData(@Valid @RequestBody NoteDto notedto,@RequestHeader("token") String token, HttpServletRequest request) throws ToDoException, IOException
	{
		String userId = (String) request.getAttribute("userId");
		logger.info(REQ_ID);
		inoteService.getMetaData(notedto,userId);
		logger.info(RESP_ID);
		return new ResponseEntity("getting metadata", HttpStatus.OK);
	}
	
	/**
	 * a method to create a label
	 * 
	 * @param labelDto
	 * @param token
	 * @return
	 * @throws ToDoException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/createlabel", method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO> createlabel(@RequestBody LabelDto labelDto, @RequestHeader String token,
			HttpServletRequest request) throws ToDoException {
		String userId = (String) request.getAttribute("userId");
		logger.info(REQ_ID);
		String labelId=inoteService.createlabel(labelDto, userId);
		logger.info(RESP_ID);
		return new ResponseEntity("label created for the labelId:"+labelId, HttpStatus.OK);
	}

	/**
	 * a method to delete a label
	 * 
	 * @param labelId
	 * @param token
	 * @return
	 * @throws ToDoException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/deletelabel/{labelId}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseDTO> deletinglabel(@PathVariable("labelId") String labelId, @RequestHeader String token,
			HttpServletRequest request) throws ToDoException {
		String userId = (String) request.getAttribute("userId");
		logger.info(REQ_ID);
		inoteService.deletelabel(labelId, userId);
		logger.info(RESP_ID);
		return new ResponseEntity("label deleted", HttpStatus.OK);
	}

	/**
	 * a method to update a label
	 * 
	 * @param labelId
	 * @param labelName
	 * @param token
	 * @return
	 * @throws ToDoException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/updatelabel/{labelId}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseDto> updatinglabel(@PathVariable("labelId") String labelId,
			@RequestParam String labelName, @RequestHeader String token, HttpServletRequest request)
			throws ToDoException {
		String userId = (String) request.getAttribute("userId");
		logger.info(REQ_ID);
		inoteService.updatelabel(labelId, labelName, userId);
		logger.info(RESP_ID);
		return new ResponseEntity(" label sucessfully updated", HttpStatus.OK);
	}

	/**
	 * a method to display all labels
	 * 
	 * @param token
	 * @return
	 * @throws ToDoException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/displayallLabel", method = RequestMethod.GET)
	public ResponseEntity<ResponseDTO> displayAllLabel(@RequestHeader String token, HttpServletRequest request)
			throws ToDoException {
		String userId = (String) request.getAttribute("userId");
		logger.info(REQ_ID + " Displaying all labels");
		List<Label> list = null;
		@SuppressWarnings("unused")
		ResponseDTO response = new ResponseDTO();
		list = inoteService.displayAllLabel(userId);
		logger.info(RESP_ID + "displayed");
		return new ResponseEntity(list, HttpStatus.OK);
	}

	/**
	 * a method to add a label to a note
	 * 
	 * @param noteId
	 * @param labelName
	 * @param token
	 * @return
	 * @throws ToDoException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/addlabel", method = RequestMethod.PUT)
	public ResponseEntity<ResponseDto> addlabel(@Valid @RequestParam String noteId, @RequestParam String labelName,
			@RequestHeader String token) throws ToDoException {
		logger.info(REQ_ID);
		inoteService.addlabel(noteId, labelName, token);
		logger.info(RESP_ID);
		return new ResponseEntity(" label sucessfully added", HttpStatus.OK);
	}
}
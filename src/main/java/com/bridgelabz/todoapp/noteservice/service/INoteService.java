package com.bridgelabz.todoapp.noteservice.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import com.bridgelabz.todoapp.exceptionservice.ToDoException;
import com.bridgelabz.todoapp.noteservice.dto.LabelDto;
import com.bridgelabz.todoapp.noteservice.dto.NoteDto;
import com.bridgelabz.todoapp.noteservice.model.Label;
import com.bridgelabz.todoapp.noteservice.model.MetaData;
import com.bridgelabz.todoapp.noteservice.model.Note;

/**
 * @author bridgelabz
 * @since 19/07/2018 <br>
 *        <p>
 *        Business entity for noteService which has declarations of functions
 *        performed on note. <br>
 *        </p>
 */
public interface INoteService {

	/**
	 * function declaration to create a note
	 * 
	 * @param note
	 * @param token
	 * @throws ToDoException
	 * @throws IOException 
	 */
	public String createNote(NoteDto notedto, String token) throws ToDoException, IOException;

	/**
	 * function declaration to delete a note
	 * 
	 * @param noteId
	 * @param token1
	 * @throws ToDoException
	 */
	public void deleteNote(String noteId, String token) throws ToDoException;

	/**
	 * function declaration to display all notes
	 * 
	 * @param token
	 * @return
	 * @throws ToDoException
	 */
	List<Note> displayAllNotes(String token) throws ToDoException;

	/**
	 * function declaration to update a note
	 * 
	 * @param noteId
	 * @param title
	 * @param description
	 * @param token
	 * @throws ToDoException
	 */
	void updateNote(String noteId, String title, String description, String token) throws ToDoException;

	/**
	 * function declaration to delete a note from trash
	 * 
	 * @param noteId
	 * @param token
	 * @throws ToDoException
	 */
	void deleteFromTrash(String noteId, String token) throws ToDoException;

	/**
	 * function declaration to restore a note from trash
	 * 
	 * @param noteId
	 * @param token
	 * @throws ToDoException
	 */
	void restoreFromTrash(String noteId, String token) throws ToDoException;

	/**
	 * function declaration to pin a note
	 * 
	 * @param noteId
	 * @param token
	 * @throws ToDoException
	 */
	void pinnote(String noteId, String token) throws ToDoException;

	/**
	 * function declaration to archive a note
	 * 
	 * @param noteId
	 * @param token
	 * @throws ToDoException
	 */
	void archivenote(String noteId, String token) throws ToDoException;

	/**
	 * @param labelDto
	 * @param token
	 * @throws ToDoException
	 */

	/**
	 * @param labelDto
	 * @param token
	 * @return 
	 * @throws ToDoException
	 */
	String createlabel(LabelDto labelDto, String token) throws ToDoException;

	/**
	 * @param noteId
	 * @param reminderTime
	 * @param token
	 * @return
	 * @throws ToDoException
	 * @throws ParseException
	 */
	Note setReminder(String noteId, String reminderTime, String token) throws ToDoException, ParseException;

	/**
	 * @param labelId
	 * @param token
	 * @throws ToDoException
	 */
	void deletelabel(String labelId, String token) throws ToDoException;

	/**
	 * @param labelId
	 * @param labelName
	 * @param token
	 * @throws ToDoException
	 */
	void updatelabel(String labelId, String labelName, String token) throws ToDoException;

	/**
	 * @param noteId
	 * @param labelName
	 * @param token
	 * @throws ToDoException
	 */
	void addlabel(String noteId, String labelName, String token) throws ToDoException;
	/**
	 * @param token
	 * @return
	 * @throws ToDoException
	 */
	List<Label> displayAllLabel(String token) throws ToDoException;
	/**
	 * @param notedto
	 * @param userId
	 * @return
	 * @throws ToDoException
	 * @throws IOException
	 */
	MetaData getMetaData(NoteDto notedto, String userId) throws ToDoException, IOException;
}

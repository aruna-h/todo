package com.bridgelabz.todoapp.noteservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.todoapp.noteservice.model.Note;

/**
 * @author bridgelabz
 * @since 18/07/2018 <br>
 *        <p>
 *        Business Entity having the Note repository related information. <br>
 *        </p>
 */

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {

	/**
	 * @param notedto
	 */
	public void save(Optional<Note> notedto);

	/**
	 * @param noteId
	 * @return
	 */
	public Optional<Note> getByNoteId(String noteId);

	/**
	 * @param userId
	 * @return
	 */
	public List<Note> findNotesByUserId(String userId);
	
}

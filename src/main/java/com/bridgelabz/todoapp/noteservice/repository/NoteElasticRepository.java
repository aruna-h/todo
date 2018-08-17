package com.bridgelabz.todoapp.noteservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.bridgelabz.todoapp.noteservice.model.Note;
/**
 * @author bridgelabz
 * @since 24/07/2018 <br>
 *        <p>
 *        Business Entity having the NoteElasticRepository related information.<br>
 *        </p>
 */
public interface NoteElasticRepository extends ElasticsearchRepository<Note,String>{

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

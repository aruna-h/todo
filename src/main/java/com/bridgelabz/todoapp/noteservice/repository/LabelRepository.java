package com.bridgelabz.todoapp.noteservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bridgelabz.todoapp.noteservice.dto.LabelDto;
import com.bridgelabz.todoapp.noteservice.model.Label;

/**
 * @author bridgelabz
 * @since 24/07/2018 <br>
 *        <p>
 *        Business Entity having the Label repository related information. <br>
 *        </p>
 */

public interface LabelRepository extends MongoRepository<Label, String> {
	/**
	 * @param label
	 */
	public void save(LabelDto label);

}

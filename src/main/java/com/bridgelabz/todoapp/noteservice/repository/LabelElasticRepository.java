package com.bridgelabz.todoapp.noteservice.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.bridgelabz.todoapp.noteservice.dto.LabelDto;
import com.bridgelabz.todoapp.noteservice.model.Label;
/**
 * @author bridgelabz
 * @since 24/07/2018 <br>
 *        <p>
 *        Business Entity having the LabelElasticRepository related information.<br>
 *        </p>
 */
public interface LabelElasticRepository extends ElasticsearchRepository<Label, String>{
	/**
	 * @param label
	 */
	public void save(LabelDto label);
}

package com.bridgelabz.todoapp.noteservice.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bridgelabz.todoapp.noteservice.model.MetaData;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author bridgelabz
 * @since 23/07/2018 <br>
 *        <p>
 *        Business Entity having the NoteDTO related information.<br>
 *        </p>
 */
@Document
public class NoteDto implements Serializable {
	
	private String userId;
@Id
	@ApiModelProperty(hidden = true)
	private String noteId;
	@NotNull
	private String title;
	@NotNull
	private String description;
	private List<LabelDto> label;
	private List<MetaData> metaDataList;
	public NoteDto() {

	}
	public NoteDto(String userId, String noteId, @NotNull String title, @NotNull String description,
			List<LabelDto> label, List<MetaData> metaDataList) {
		super();
		this.userId = userId;
		this.noteId = noteId;
		this.title = title;
		this.description = description;
		this.label = label;
		this.metaDataList = metaDataList;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNoteId() {
		return noteId;
	}
	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<LabelDto> getLabel() {
		return label;
	}
	public void setLabel(List<LabelDto> label) {
		this.label = label;
	}
	public List<MetaData> getMetaDataList() {
		return metaDataList;
	}
	public void setMetaDataList(List<MetaData> metaDataList) {
		this.metaDataList = metaDataList;
	}
	@Override
	public String toString() {
		return "NoteDto [userId=" + userId + ", noteId=" + noteId + ", title=" + title + ", description=" + description
				+ ", label=" + label + ", metaDataList=" + metaDataList + "]";
	}
}

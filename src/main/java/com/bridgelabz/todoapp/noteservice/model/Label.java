package com.bridgelabz.todoapp.noteservice.model;

import java.util.List;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.bridgelabz.todoapp.noteservice.dto.LabelDto;

/**
 * @author bridgelabz
 * @since 23/07/2018 <br>
 *        <p>
 *        Business Entity having the label related information.<br>
 *        </p>
 */
@Document(indexName="testlabel",type="label")
public class Label {
	private String userId;
	private String NoteId;
	@Id
	private String labelId;
	private String labelName;

	public Label() {

	}

	public Label(String labelId, String labelName, String userId, String noteId) {
		this.labelId = labelId;
		this.labelName = labelName;
		this.userId = userId;
		NoteId = noteId;
	}

	public String getLabelId() {
		return labelId;
	}

	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNoteId() {
		return NoteId;
	}

	public void setNoteId(String noteId) {
		NoteId = noteId;
	}

	@Override
	public String toString() {
		return "Label [userId=" + userId + ", NoteId=" + NoteId + ", labelId=" + labelId + ", labelName=" + labelName
				+ "]";
	}
	
}

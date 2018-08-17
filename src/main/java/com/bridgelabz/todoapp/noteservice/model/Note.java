package com.bridgelabz.todoapp.noteservice.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author bridgelabz
 * @since 18/07/2018 <br>
 *        <p>
 *        Business Entity having the Note related information.<br>
 *        </p>
 */
@Document(indexName = "test", type = "note")
public class Note {

	private String userId;
	@Id
	@NotNull
	private String noteId;
	@NotNull
	private String title;
	@NotNull
	private String description;
	private String createdAt;
	private String updatedAt;
	private String remindMe;
	private boolean trashStatus = false;
	private boolean pinnedStatus = false;
	private boolean archivestatus = false;
	private String labelname;

	private List<Label> label;
	private List<MetaData> metaDataList;

	public Note(String userId, @NotNull String noteId, @NotNull String title, @NotNull String description,
			String createdAt, String updatedAt, String remindMe, boolean trashStatus, boolean pinnedStatus,
			boolean archivestatus, String labelname, List<Label> label, List<MetaData> metaDataList) {
		super();
		this.userId = userId;
		this.noteId = noteId;
		this.title = title;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.remindMe = remindMe;
		this.trashStatus = trashStatus;
		this.pinnedStatus = pinnedStatus;
		this.archivestatus = archivestatus;
		this.labelname = labelname;
		this.label = label;
		this.metaDataList = metaDataList;
	}

	public Note() {
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

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getRemindMe() {
		return remindMe;
	}

	public void setRemindMe(String remindMe) {
		this.remindMe = remindMe;
	}

	public boolean isTrashStatus() {
		return trashStatus;
	}

	public void setTrashStatus(boolean trashStatus) {
		this.trashStatus = trashStatus;
	}

	public boolean isPinnedStatus() {
		return pinnedStatus;
	}

	public void setPinnedStatus(boolean pinnedStatus) {
		this.pinnedStatus = pinnedStatus;
	}

	public boolean isArchivestatus() {
		return archivestatus;
	}

	public void setArchivestatus(boolean archivestatus) {
		this.archivestatus = archivestatus;
	}

	public String getLabelname() {
		return labelname;
	}

	public void setLabelname(String labelname) {
		this.labelname = labelname;
	}

	public List<Label> getLabel() {
		return label;
	}

	public void setLabel(List<Label> label) {
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
		return "Note [userId=" + userId + ", noteId=" + noteId + ", title=" + title + ", description=" + description
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", remindMe=" + remindMe + ", trashStatus="
				+ trashStatus + ", pinnedStatus=" + pinnedStatus + ", archivestatus=" + archivestatus + ", labelname="
				+ labelname + ", label=" + label + ", metaDataList=" + metaDataList + "]";
	}

}

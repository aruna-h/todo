package com.bridgelabz.todoapp.noteservice.model;
/**
 * @author bridgelabz
 * @since 5/08/2018 <br>
 *        <p>
 *        Entity having the Metadata of a note description related information.<br>
 *        </p>
 */
public class MetaData {
	String url;
	String title;
	String imageUrl;

	public MetaData() {

	}

	public MetaData(String url, String title, String imageUrl) {
		super();
		this.url = url;
		this.title = title;
		this.imageUrl = imageUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "MetaData [url=" + url + ", title=" + title + ", imageUrl=" + imageUrl + "]";
	}

}

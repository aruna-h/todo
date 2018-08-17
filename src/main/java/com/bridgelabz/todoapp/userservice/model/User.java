package com.bridgelabz.todoapp.userservice.model;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author bridgelabz
 * @since 12/July/2018 <br>
 *        <p>
 *        Business Entity having the User related information.<br>
 *        </p>
 */
@Document(collection="user")
public class User {
	@Id
	
	private String id;

	private String userName;

	private String password;

	private String firstName;

	private String lastName;

	private String mobNumber;

	private String email;
	private boolean isActivate;

	public boolean isActivate() {
		return isActivate;
}
	public User() {

	}

	public User(String id, String userName, String password, String firstName, String lastName, String mobNumber,
			String email) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobNumber = mobNumber;
		this.email = email;
	}
	public void setIsActivate(boolean isActivate) {
		this.isActivate = isActivate;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobNumber() {
		return mobNumber;
	}

	public void setMobNumber(String mobNumber) {
		this.mobNumber = mobNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", mobNumber=" + mobNumber + ", email=" + email + "]";
	}
}

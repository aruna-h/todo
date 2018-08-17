package com.bridgelabz.todoapp.userservice.dto;

/**
 * @author bridgelabz
 * @since 12/7/2018 <br>
 *        <p>
 *        a business entity for resetting the password of a user
 *        </p>
 */
public class ResetPasswordDTO {
	String newPassword;
	String confirmPassword;
	
	public ResetPasswordDTO() {
		
	}

	/**
	 * @param newPassword
	 * @param confirmPassword
	 */
	public ResetPasswordDTO(String newPassword, String confirmPassword) {
		super();
		this.newPassword = newPassword;
		this.confirmPassword = confirmPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String toString() {
		return "ResetPasswordDTO [newPassword=" + newPassword + ", confirmPassword=" + confirmPassword + "]";
	}

}

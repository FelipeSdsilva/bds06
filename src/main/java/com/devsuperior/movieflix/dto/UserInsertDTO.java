package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.services.validations.UserInsertValid;

@UserInsertValid
public class UserInsertDTO extends UserDTO {
	private static final long serialVersionUID = 1L;

	private String password;

	UserInsertDTO() {
		super();
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

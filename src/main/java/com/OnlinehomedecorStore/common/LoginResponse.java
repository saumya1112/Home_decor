package com.OnlinehomedecorStore.common;

import com.OnlinehomedecorStore.entities.UserModel;

public class LoginResponse {
	private UserModel user;
	private String message;

	public LoginResponse(UserModel user, String message) {
		this.user = user;
		this.message = message;
	}

	public LoginResponse() {
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

package com.midterm.springcommerce.Utilities.Payloads.Requests;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
	@NotBlank(message = "The username is required.")
	private String username;

	@NotBlank(message = "The password is required.")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

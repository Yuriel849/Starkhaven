package com.yuriel.domain;

public class AuthDTO {
	private String email;
	private String authCode;
	
	@Override
	public String toString() {
		return "AuthDTO [email=" + email + ", authCode=" + authCode + "]";
	}
	
	// getters & setters
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
}
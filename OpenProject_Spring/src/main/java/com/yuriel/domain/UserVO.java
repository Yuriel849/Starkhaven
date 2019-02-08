package com.yuriel.domain;

import org.springframework.web.multipart.MultipartFile;

public class UserVO {
	private String id;
	private String email;
	private String name;
	private String pw;
	private String fullName;
	private MultipartFile img = null;
	
	@Override
	public String toString() {
		return "UserVO [id=" + id + ", email=" + email + ", name=" + name + ", pw=" + pw + ", fullName=" + fullName
				+ ", img=" + img + "]";
	}

	// getters & setters
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public MultipartFile getImg() {
		return img;
	}
	public void setImg(MultipartFile img) {
		this.img = img;
	}
}
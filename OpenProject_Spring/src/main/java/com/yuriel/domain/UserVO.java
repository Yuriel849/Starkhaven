package com.yuriel.domain;

import java.io.File;

public class UserVO {
	private String id;
	private String email;
	private String name;
	private String pw;
	private File img = null; // 나중에 file upload 기능 추가
	
	@Override
	public String toString() {
		return "UserVO [id=" + id + ", email=" + email + ", pw=" + pw + ", name=" + name + ", img=" + img + "]";
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
	public File getImg() {
		return img;
	}
	public void setImg(File img) {
		this.img = img;
	}
}
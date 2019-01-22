package com.dto;

import java.io.File;

public class User {
	private String id;
	private String pw;
	private String name;
	private File img = null; // 나중에 file upload 기능 추가
	
	// DTO Default Constructor
	public User () {}

	// DTO Constructor
	public User (String ID, String PW, String Name) {
		super();
		this.id = ID;
		this.pw = PW;
		this.name = Name;
	}

	// DTO Getters & Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
} //  클래스 끝.
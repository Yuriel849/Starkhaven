package dto;

import java.io.File;

public class User {
	private String ID;
	private String PW;
	private String Name;
	private File Img = null; // 나중에 file upload 기능 추가
	
	// DTO Default Constructor
	public User () {}

	// DTO Constructor
	public User (String ID, String PW, String Name) {
		super();
		this.ID = ID;
		this.PW = PW;
		this.Name = Name;
	}

	// DTO Getters & Setters
	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getPW() {
		return PW;
	}

	public void setPW(String PW) {
		this.PW = PW;
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}
} //  클래스 끝.
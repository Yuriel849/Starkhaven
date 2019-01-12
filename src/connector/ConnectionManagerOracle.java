package connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionManagerOracle {
	// Connection을 만드는데 사용할 Oracle DB의 URL, userID, password 기본값 명시 (getter & setter로 바꿀 수 있다)
	private String dbURL = "jdbc:oracle:thin:@localhost:1521:XE";
	private String dbUSER = "Yuriel";
	private String dbPassword = "Sapph1r3";
	
	// query마다 개별적인 conn(DB와의 연결)을 가지고 있어야 한다.
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String table = "";

	// getters and setters to change the dbURL, dbUSER, and dbPassword
	public String getDbURL() {
		return dbURL;
	}
	public void setDbURL(String dbURL) {
		this.dbURL = dbURL;
	}
	public String getDbUSER() {
		return dbUSER;
	}
	public void setDbUSER(String dbUSER) {
		this.dbUSER = dbUSER;
	}
	public String getDbPassword() {
		return dbPassword;
	}
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}
	
	
	try {
		
	} catch()
}

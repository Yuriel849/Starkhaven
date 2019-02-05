package connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagerOracle {
	// Connection을 만드는데 사용할 Oracle DB의 URL, userID, password 기본값 명시 (getter & setter로 바꿀 수 있다)
	private String dbURL = "jdbc:oracle:thin:@localhost:1521:XE";
	private String dbUser = "Yuriel";
	private String dbPassword = "Sapph1r3";
	
	// query마다 개별적인 conn(DB와의 연결)을 가지고 있어야 한다.
	protected Connection conn = null;

	// default constructor
	protected ConnectionManagerOracle() {
		this(null);
	}
	
	// constructor
	protected ConnectionManagerOracle(Connection conn) {
		// 생성자 호출 시 conn == null이면 Connection이 필요하니까 getConnection() 호출
		if(conn != null) {this.conn = conn;}
		else {this.conn = getConnection();}
	}
	
	// getters and setters to change the dbURL, dbUSER, and dbPassword
	public String getDbURL() {
		return dbURL;
	}
	public void setDbURL(String dbURL) {
		this.dbURL = dbURL;
	}
	public String getDbUser() {
		return dbUser;
	}
	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}
	public String getDbPassword() {
		return dbPassword;
	}
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}
	
	// 별도로 Connection이 제공되지 않는다면 여기서 Connection을 새로 만들어서 제공한다.
	Connection getConnection() {
		try {
			// Loader 클래스 사용하기에 Class.forName() 불필요!
//			Class.forName("oracle.jdbc.driver.OracleDriver"); // JDBC 드라이버를 로딩한다.
			conn = DriverManager.getConnection(dbURL, dbUser, dbPassword); // 데이터베이스와 연결을 하여 그 연결을 "conn" 참조변수로 다룬다.
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	 
	void rollback(){
		if(conn != null) {
			try {
				conn.rollback();
			} catch(SQLException e){ }
		}
	}
 
	// 가변인자를 사용 -> AutoCloseable한 객체를 여럿 입력하면 하나씩 close() 호출하면서 끝낸다.
	void close(AutoCloseable... acs) {
		try {
			for(AutoCloseable auto : acs) {
				auto.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
} // ConnectionManagerOracle 클래스 끝.
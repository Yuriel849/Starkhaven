package connector;

import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class DBCPInit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		loadJDBCDriver();
		initConnectionPool();
	}
	
	private void loadJDBCDriver() {
		/* web.xml의 <servlet> 태그를 이용해서 초기화 파라미터를 전달 받는 이유 >>
		 *     자바 클래스를 한 번 컴파일한 후 재컴파일 없이 초기화에 필요한 값을 수정하기 위해서!
		 *     만약 이렇게 사용하지 않는다면, 초기화에 필요한 값을 수정할 때마다 해당 클래스를 재컴파일해야 한다 (재컴파일 시 예전에는 없었던 에러가 생길 수도 있다)
		 *         "초기화에 필요한 값" -> 여기서는 driverClass, jdbcUrl, dbUser, dbPassword 등
		 */
		String driverClass = getInitParameter("jdbcdriver"); // web.xml에 지정한 초기화 파라미터의 값을 읽어온다.
		if(driverClass == null) { driverClass = "oracle.jdbc.driver.OracleDriver"; } // 만약 초기화 파라미터를 찾을 수 없을 경우를 위해
		try {
			// Connection Pool 내부적으로 사용할 JDBC 드라이버 로딩
			Class.forName(driverClass);
			
			System.out.println("::::: DBCPInit --> JDBC DRIVER LOADED :::::");
		} catch(ClassNotFoundException e) {
			throw new RuntimeException("JDBC driver load failure", e);
		}
	}
	
	private void initConnectionPool() {
		try {
			String jdbcUrl = getInitParameter("jdbcUrl");
			String dbUser = getInitParameter("dbUser");
			String dbPassword = getInitParameter("dbPassword");
			
			if(jdbcUrl == null) { jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE"; }
			if(dbUser == null) { dbUser = "Yuriel"; }
			if(dbPassword == null) { dbPassword = "Sapph1r3"; }
			
			ConnectionFactory connFactory = new DriverManagerConnectionFactory(jdbcUrl, dbUser, dbPassword);
			
			PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory(connFactory, null);
			poolableConnFactory.setValidationQuery("select 1");
			
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
			poolConfig.setTestWhileIdle(true);
			poolConfig.setMinIdle(4);
			poolConfig.setMaxTotal(50);
			
			GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnFactory, poolConfig);
			
			poolableConnFactory.setPool(connectionPool);
			
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			String poolName = getInitParameter("poolName");
			if(poolName == null) { poolName = "connections"; }
			driver.registerPool(poolName, connectionPool);
			
			System.out.println("::::: DBCPInit --> CONNECTION POOL READY :::::");
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}

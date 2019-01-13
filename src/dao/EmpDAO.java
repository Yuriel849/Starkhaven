package dao;

import java.sql.PreparedStatement;
import connector.ConnectionManagerOracle;
import dto.Employee;

public class EmpDAO extends ConnectionManagerOracle {
	// Singleton pattern
	private static EmpDAO eDAO = new EmpDAO();
	private EmpDAO() {}
	
	// 외부에서 생성자에 직접 접근 못하니까 getInstance()로 대신 객체를 반환받도록.
	public static EmpDAO getInstance() {
		if(eDAO == null) { // Singleton 패턴 -> EmpDAO 객체가 단 하나만 만들어질 수 있도록 제어
			eDAO = new EmpDAO();
		}
		return eDAO;
	}
		
	// Execute "INSERT" query
	// ex) insert into emp values(7566, 'JONES', 'MANAGER', 7839, to_date('2-4-1981','dd-mm-yyyy'), 2975, null, 20)
	// empno number(4,0), ename varchar2(10), job varchar2(9), mgr number(4,0), hiredate date, sal number(7,2), comm number(7,2), deptno number(2,0)
	public static int insert(Employee emp) {
		try {
			String query = "INSERT INTO EMP VALUES(?, ?, ?, ?, to_date(?, 'dd-mm-yyyy'), ?, ?, ?)";
			PreparedStatement pstmt = eDAO.conn.prepareStatement(query);
			pstmt.setInt(1, emp.getEMPNO());
			pstmt.setString(2, emp.getENAME());
			pstmt.setString(3, emp.getJOB());
			pstmt.setInt(4, emp.getMGR());
			pstmt.setString(5, emp.getHIREDATE());
			pstmt.setDouble(6, emp.getSAL());
			pstmt.setDouble(7, emp.getCOMM());
			pstmt.setInt(8, emp.getDEPTNO());
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return -1; // 에러가 일어났다면 실행된다 -> 제대로 실행되지 않았다는 의미 -> 제대로 실행된 경우 1이나 0을 반환하니까
	}
}

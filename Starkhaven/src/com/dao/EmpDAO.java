package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dto.Employee;

import connector.ConnectionProvider;
import connector.JDBCUtil;

public class EmpDAO {
	// Singleton 패턴 -> EmpDAO 객체가 단 하나만 만들어질 수 있도록 제어
	private static EmpDAO eDao = new EmpDAO();
	
	private EmpDAO() {
		super();
	}
	
	// 외부에서 생성자에 직접 접근 못하니까 getInstance()로 대신 객체를 반환받도록.
	public static EmpDAO getInstance() {
		if(eDao == null) { eDao = new EmpDAO(); }
		
		return eDao;
	}
	
	// Execute "SELECT WHERE EMPNO = ?" query
	public Employee selectByEmpNo(Connection conn, String empno) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Employee wrap = new Employee();
		
		try {
			String query = "SELECt * FROM EMP WHERE EMPNO = ?";
						
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, empno);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				wrap.setEmpno(rs.getInt(1));
				wrap.setEname(rs.getString(2));
				wrap.setJob(rs.getString(3));
				wrap.setMgr(rs.getInt(4));
				wrap.setHiredate(rs.getString(5));
				wrap.setSal(rs.getDouble(6));
				wrap.setComm(rs.getDouble(7));
				wrap.setDeptno(rs.getInt(8));
			}
		} catch(Exception e) {
		 	e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(rs);
		}
		
		return wrap;
	} // selectByEmpNo() 끝.
	
	// Execute "SELECT *" query
	public List<Employee> selectAllEmp(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
    	
    	try {
			String query = "SELECT * FROM EMP";
    		
    		pstmt = conn.prepareStatement(query);
    		rs = pstmt.executeQuery();
    		
    		List<Employee> list = new ArrayList<>();
    		while (rs.next()) {
    			int empno = rs.getInt(1);
    			String ename = rs.getString(2);
    			String job = rs.getString(3);
    			int mgr = rs.getInt(4);
    			String hiredate = rs.getString(5);
    			double sal = rs.getDouble(6);
    			double comm = rs.getDouble(6);
    			int deptno = rs.getInt(6);
    			list.add(new Employee(empno, ename, job, mgr, hiredate, sal, comm, deptno));
    		}
    		return list;
    	} catch(Exception e) {
    		e.printStackTrace();
       	} finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(rs);
		}
		return null;
    } // selectAllEmp() 끝.

	// Execute "SELECT * WHERE ROWNUM, * BETWEEN A AND B" query
	public List<Employee> selectEmp(Connection conn, int firstRow, int lastRow) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
    	
    	try {
			String query = "SELECT * FROM (SELECT ROWNUM RN, S.* FROM (SELECT * FROM EMP ORDER BY EMP.EMPNO ASC) S) WHERE RN BETWEEN ? AND ?";
    		
    		pstmt = conn.prepareStatement(query);
    		pstmt.setInt(1, firstRow);
    		pstmt.setInt(2, lastRow);
    		rs = pstmt.executeQuery();
    		
    		List<Employee> list = new ArrayList<>();
    		while (rs.next()) {
    			int empno = rs.getInt(2);
    			String ename = rs.getString(3);
    			String job = rs.getString(4);
    			int mgr = rs.getInt(5);
    			String hiredate = rs.getString(6);
    			double sal = rs.getDouble(7);
    			double comm = rs.getDouble(8);
    			int deptno = rs.getInt(9);
    			list.add(new Employee(empno, ename, job, mgr, hiredate, sal, comm, deptno));
    		}
    		return list;
    	} catch(Exception e) {
    		e.printStackTrace();
       	} finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(rs);
		}
		return null;
    } // selectEmp() 끝.
	
	// Execute "SELECT COUNT(*)" query
	public int selectCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
    	
    	try {
			String query = "SELECT COUNT(*) FROM EMP";
    		
    		pstmt = conn.prepareStatement(query);
    		rs = pstmt.executeQuery();

    		while (rs.next()) {
    			cnt = rs.getInt(1); // EMP 테이블에 레코드가 몇 개있는지 int 값을 저장한다.
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
       	} finally {
			JDBCUtil.close(pstmt);
			JDBCUtil.close(rs);
		}
    	return cnt;
    } // selectCount() 끝.
	
	// Execute "INSERT" query
	public int insert(Connection conn, Employee emp) {
		PreparedStatement pstmt = null;
		
		try {
			String query = "INSERT INTO EMP VALUES(?, ?, ?, ?, to_date(?, 'yyyy-mm-dd'), ?, ?, ?)";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, emp.getEmpno());
			pstmt.setString(2, emp.getEname());
			pstmt.setString(3, emp.getJob());
			pstmt.setInt(4, emp.getMgr());
			pstmt.setString(5, emp.getHiredate());
			pstmt.setDouble(6, emp.getSal());
			pstmt.setDouble(7, emp.getComm());
			pstmt.setInt(8, emp.getDeptno());
			return pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		
		return -1; // 에러가 일어났다면 실행된다 -> 제대로 실행되지 않았다는 의미 -> executeUpdate()은 제대로 실행된 경우 1이나 0을 반환하니까
	} // insert() 끝.
	
	// Execute "UPDATE" query
	public int update(Connection conn, Employee emp) {
		PreparedStatement pstmt = null;
		
		try {
			String query = "UPDATE EMP SET ENAME = ?, JOB = ?, MGR = ?, HIREDATE = to_date(?, 'yyyy-mm-dd'), SAL = ?, COMM = ?, DEPTNO = ? WHERE EMPNO = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, emp.getEname());
			pstmt.setString(2, emp.getJob());
			pstmt.setInt(3, emp.getMgr());
			pstmt.setString(4, emp.getHiredate());
			pstmt.setDouble(5, emp.getSal());
			pstmt.setDouble(6, emp.getComm());
			pstmt.setInt(7, emp.getDeptno());
			pstmt.setInt(8, emp.getEmpno());
			System.out.println(pstmt);
			return pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		
		return -1; // 에러가 일어났다면 실행된다 -> 제대로 실행되지 않았다는 의미 -> executeUpdate()은 제대로 실행된 경우 1이나 0을 반환하니까
	} // update() 끝.
	
	// Execute "DELETE" query
	public int delete(Connection conn, String empno) {
		PreparedStatement pstmt = null;

		try {
			String query = "DELETE FROM EMP WHERE EMPNO = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, empno);
			return pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		
		return -1; // 에러가 일어났다면 실행된다 -> 제대로 실행되지 않았다는 의미 -> executeUpdate()은 제대로 실행된 경우 1이나 0을 반환하니까
	} // delete() 끝.
}
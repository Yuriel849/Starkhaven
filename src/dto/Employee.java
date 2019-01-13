package dto;

public class Employee {
	private int EMPNO;
	private String ENAME;
	private String JOB;
	private int MGR;
	private String HIREDATE;
	private double SAL;
	private double COMM;
	private int DEPTNO;
	
	// DTO Default Constructor
	public Employee() {}
	
	// DTO Constructor
	public Employee(int empno, String ename, String job, int mgr, String hireDate, double sal, double comm, int deptno) {
		this.EMPNO = empno;
		this.ENAME = ename;
		this.JOB = job;
		this.MGR = mgr;
		this.HIREDATE = hireDate;
		this.SAL = sal;
		this.COMM = comm;
		this.DEPTNO = deptno;
	}
	
	// DTO Getters & Setters
	public int getEMPNO() {
		return this.EMPNO;
	}
	public void setEMPNO(int empno) {
		this.EMPNO = empno;
	}
	public String getENAME() {
		return this.ENAME;
	}
	public void setENAME(String ename) {
		this.ENAME = ename;
	}
	public String getJOB() {
		return this.JOB;
	}
	public void setJOB(String job) {
		this.JOB = job;
	}
	public int getMGR() {
		return this.MGR;
	}
	public void setMGR(int mgr) {
		this.MGR = mgr;
	}
	public String getHIREDATE() {
		return this.HIREDATE;
	}
	public void setHIREDATE(String hiredate) {
		this.HIREDATE = hiredate;
	}
	public double getSAL() {
		return this.SAL;
	}
	public void setSAL(double sal) {
		this.SAL = sal;
	}
	public double getCOMM() {
		return this.COMM;
	}
	public void setCOMM(double comm) {
		this.COMM = comm;
	}
	public int getDEPTNO() {
		return this.DEPTNO;
	}
	public void setDEPTNO(int deptno) {
		this.DEPTNO = deptno;
	}
} // Employee 클래스 끝.
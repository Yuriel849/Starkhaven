package com.dto;

public class Employee {
	private int empno;
	private String ename;
	private String job;
	private int mgr;
	private String hiredate;
	private double sal;
	private double comm;
	private int deptno;
	
	// DTO Default Constructor
	public Employee() {}

	// DTO Constructor
	public Employee(int empno, String ename, String job, int mgr, String hiredate, double sal, double comm, int deptno) {
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.mgr = mgr;
		this.hiredate = hiredate;
		cutTime();
		this.sal = sal;
		this.comm = comm;
		this.deptno = deptno;
	}
	
	
	private void cutTime() {
		int place = hiredate.indexOf(':');
		if(place != -1) {
			hiredate = hiredate.substring(0, place-2);
		}
	}
	
	// DTO Getters & Setters
	public int getEmpno() {
		return this.empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEname() {
		return this.ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return this.job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getMgr() {
		return this.mgr;
	}
	public void setMgr(int mgr) {
		this.mgr = mgr;
	}
	public String getHiredate() {
		return this.hiredate;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	public double getSal() {
		return this.sal;
	}
	public void setSal(double sal) {
		this.sal = sal;
	}
	public double getComm() {
		return this.comm;
	}
	public void setComm(double comm) {
		this.comm = comm;
	}
	public int getDeptno() {
		return this.deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
} // Employee 클래스 끝.
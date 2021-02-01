package edu.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Prakash
 *
 */
public class EmployeeDto implements Serializable{
	private int eid;
	private String ename;
	private double salary;
	private Date hiredate;

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

}

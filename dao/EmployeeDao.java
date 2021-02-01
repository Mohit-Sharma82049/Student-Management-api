package edu.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import edu.dto.EmployeeDto;
import edu.exception.EmployeeException;
import edu.util.DBUtil;

/**
 * @author Prakash
 *
 */
public class EmployeeDao {
	private static EmployeeDao employeeDao = new EmployeeDao();

	private EmployeeDao() {

	}

	public static EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	public boolean insertEmployee(EmployeeDto employeeDto) throws EmployeeException {
		boolean flag = false;
		Connection con = null;
		PreparedStatement ps = null;
		String insertQuery = "Insert into Employee values(Employee_eid_seq.NEXTVAL,?,?,?)";
		try {
			con = DBUtil.connect();
			ps = con.prepareStatement(insertQuery);
			ps.setString(1, employeeDto.getEname());
			ps.setDouble(2, employeeDto.getSalary());
			java.util.Date utilDate = employeeDto.getHiredate();
			Date hiredate = new Date(utilDate.getTime());
			ps.setDate(3, hiredate);
			if (ps.executeUpdate() > 0) {
				ps = con.prepareStatement("SELECT Employee_eid_seq.CURRVAL FROM Dual");
				ResultSet rs = ps.executeQuery();
				rs.next();
				employeeDto.setEid(rs.getInt(1));
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("EmployeeDao : insertEmployee E : " + e);
			throw new EmployeeException("Failed to Employee insert operation ! ");
		}
		return flag;
	}

	public boolean deleteEmployee(int eid) throws EmployeeException {
		boolean flag = false;
		Connection con = null;
		PreparedStatement ps = null;
		String deleteQuery = "DELETE FROM Employee where eid=?";
		try {
			con = DBUtil.connect();
			ps = con.prepareStatement(deleteQuery);
			ps.setInt(1, eid);
			if (ps.executeUpdate() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("EmployeeDao: deleteEmployee E ");
			throw new EmployeeException("Failed to delete operation !");
		}
		return flag;
	}

	public EmployeeDto selectEmployee(int eid) throws EmployeeException {
		EmployeeDto employeeDto = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String selectQuery = "Select eid,ename,salary,hiredate from Employee WHERE eid = ?";
		try {
			con = DBUtil.connect();
			ps = con.prepareStatement(selectQuery);
			ps.setInt(1, eid);
			rs = ps.executeQuery();
			if (rs.next()) {
				employeeDto = new EmployeeDto();
				employeeDto.setEid(rs.getInt(1));
				employeeDto.setEname(rs.getString(2));
				employeeDto.setSalary(rs.getDouble(3));
				Date sqlDate = rs.getDate(4);
				java.util.Date hiredate = new java.util.Date(sqlDate.getDate());
				employeeDto.setHiredate(hiredate);

			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("EmployeeDao: selectEmployee()- " + e);
			throw new EmployeeException("Failed to Select Employee!");
		}
		return employeeDto;
	}

	public ArrayList<EmployeeDto> selectAllEmployee() throws EmployeeException {
		EmployeeDto employeeDto = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String selectQuery = "Select eid,ename,salary,hiredate from Employee";
		ArrayList<EmployeeDto> al = new ArrayList<EmployeeDto>();
		try {
			con = DBUtil.connect();
			ps = con.prepareStatement(selectQuery);
			rs = ps.executeQuery();
			while (rs.next()) {
				employeeDto = new EmployeeDto();
				employeeDto.setEid(rs.getInt(1));
				employeeDto.setEname(rs.getString(2));
				employeeDto.setSalary(rs.getDouble(3));
				Date sqlDate = rs.getDate(4);
				java.util.Date hiredate = new java.util.Date(sqlDate.getDate());
				employeeDto.setHiredate(hiredate);
				al.add(employeeDto);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("EmployeeDao: selectAllEmployee()- " + e);
			throw new EmployeeException("Failed to Select All Employee!");
		}
		return al;
	}

}

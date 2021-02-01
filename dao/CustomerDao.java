package edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import edu.dto.CustomerDto;
import edu.exception.CustomerException;
import edu.util.DBUtil;

/**
 * @author Prakash
 *
 */
public class CustomerDao {
	private static CustomerDao customerDao = new CustomerDao();

	private CustomerDao() {

	}

	public static CustomerDao getCustomerDao() {
		return customerDao;
	}

	public static boolean insertCustomer(CustomerDto customerDto) throws CustomerException {
		boolean flag = false;
		Connection con = null;
		PreparedStatement ps = null;
		String insertQuery = "INSERT INTO Customer values(Customer_accno_seq.NEXTVAL,?,?,?,?)";
		try {
			con = DBUtil.connect();
			ps = con.prepareStatement(insertQuery);
			ps.setString(1, customerDto.getCname());
			ps.setDouble(2, customerDto.getBalance());
			Date utilDate = customerDto.getDob();
			java.sql.Date dob = new java.sql.Date(utilDate.getTime());
			ps.setDate(3, dob);
			ps.setString(4, customerDto.getAddress());
			if (ps.executeUpdate() > 0) {
				ps = con.prepareStatement("SELECT CUSTOMER_ACCNO_SEQ.CURRVAL FROM DUAL");
				ResultSet rs = ps.executeQuery();
				rs.next();
				customerDto.setAccno(rs.getInt(1));
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("CustomerDao : insertCustomer E : " + e);
			throw new CustomerException("Failed to Customer insertion operation");
		}
		return flag;
	}

	public static boolean deleteCustomer(int accno) throws CustomerException {
		boolean flag = false;
		Connection con = null;
		PreparedStatement ps = null;
		String deleteQuery = "DELETE FROM CUSTOMER WHERE ACCNO = ?";
		try {
			con = DBUtil.connect();
			ps = con.prepareStatement(deleteQuery);
			ps.setInt(1, accno);
			if (ps.executeUpdate() > 0)
				flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("CustomerDao : deleteCustomer E : " + e);
			throw new CustomerException("Failed to Customer deletion operation");
		}
		return flag;
	}

	public CustomerDto selectCustomer(int accno) throws CustomerException {
		CustomerDto customerDto = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String selectQuery = "Select * from Customer WHERE accno = ?";
		try {
			con = DBUtil.connect();
			ps = con.prepareStatement(selectQuery);
			ps.setInt(1, accno);
			rs = ps.executeQuery();
			if (rs.next()) {
				customerDto = new CustomerDto();
				customerDto.setAccno(rs.getInt(1));
				customerDto.setCname(rs.getString(2));
				customerDto.setBalance(rs.getDouble(3));
				Date sqlDate = rs.getDate(4);
				java.util.Date dob = new java.util.Date(sqlDate.getDate());
				customerDto.setDob(dob);
				customerDto.setAddress(rs.getString(5));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("CustomerDao: selectCustomerr()- " + e);
			throw new CustomerException("Failed to Select Customer!");
		}
		return customerDto;
	}

	public ArrayList<CustomerDto> selectAllCustomer() throws CustomerException {
		CustomerDto customerDto = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String selectQuery = "Select * from Customer WHERE";
		ArrayList<CustomerDto> al = new ArrayList<CustomerDto>();
		try {
			con = DBUtil.connect();
			ps = con.prepareStatement(selectQuery);
			rs = ps.executeQuery();
			while (rs.next()) {
				customerDto = new CustomerDto();
				customerDto.setAccno(rs.getInt(1));
				customerDto.setCname(rs.getString(2));
				customerDto.setBalance(rs.getDouble(3));
				Date sqlDate = rs.getDate(4);
				java.util.Date dob = new java.util.Date(sqlDate.getDate());
				customerDto.setDob(dob);
				customerDto.setAddress(rs.getString(5));
				al.add(customerDto);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("CustomerDao: selectCustomerr()- " + e);
			throw new CustomerException("Failed to Select Customer!");
		}
		return al;
	}

}

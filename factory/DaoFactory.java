package edu.factory;

import edu.dao.CustomerDao;
import edu.dao.EmployeeDao;

public class DaoFactory {
	public static EmployeeDao getEmployeeDao() {
		return EmployeeDao.getEmployeeDao();
	}

	public static CustomerDao getCustomerDao() {
		return CustomerDao.getCustomerDao();
	}
}

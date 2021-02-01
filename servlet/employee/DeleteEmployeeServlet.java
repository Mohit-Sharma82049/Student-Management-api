package edu.servlet.employee;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.dao.EmployeeDao;
import edu.factory.DaoFactory;

public class DeleteEmployeeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("eid");
		EmployeeDao employeeDao = null;
		RequestDispatcher rd = null;
		try {
			Integer eid = Integer.parseInt(id);
			employeeDao = DaoFactory.getEmployeeDao();
			if (employeeDao.deleteEmployee(eid)) {
				rd = request.getRequestDispatcher("deleteemployeesuccess.jsp");
			} else
				rd = request.getRequestDispatcher("deleteemployeefailure.jsp");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Delete employee Servlet!" + e);
			rd = request.getRequestDispatcher("deleteemployeeerror.jsp");
		}
		rd.forward(request, response);
	}

}

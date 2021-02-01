package edu.servlet.employee;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.dao.EmployeeDao;
import edu.dto.EmployeeDto;
import edu.factory.DaoFactory;

public class SelectEmployeeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("eid");
		EmployeeDto employeeDto = null;
		RequestDispatcher rd = null;
		try {
			Integer eid = Integer.parseInt(id);
			EmployeeDao employeeDao = DaoFactory.getEmployeeDao();
			employeeDto = employeeDao.selectEmployee(eid);
			if (employeeDto != null) {
				request.setAttribute("employee", employeeDto);
				rd = request.getRequestDispatcher("selectemployeesuccess.jsp");
			} else
				rd = request.getRequestDispatcher("selectemployeefailure.jsp");

		} catch (Exception e) {
			System.out.println("SelectEmployeeServlet: doGet()-" + e);
			rd = request.getRequestDispatcher("selectemployeeerror.jsp");
		}
		rd.forward(request, response);
	}

}

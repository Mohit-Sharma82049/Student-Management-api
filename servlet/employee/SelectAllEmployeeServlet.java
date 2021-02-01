package edu.servlet.employee;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.dao.EmployeeDao;
import edu.dto.EmployeeDto;
import edu.factory.DaoFactory;

public class SelectAllEmployeeServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = null;
		ArrayList<EmployeeDto> list = null;
		try {

			EmployeeDao employeeDao = DaoFactory.getEmployeeDao();
			list = employeeDao.selectAllEmployee();
			if (list.size() > 0) {
				request.setAttribute("list", list);
				rd = request.getRequestDispatcher("selectallemployeesuccess.jsp");
			} else
				rd = request.getRequestDispatcher("selectallemployeefailure.jsp");

		} catch (Exception e) {
			System.out.println("SelectEmployeeServlet: doGet()-" + e);
			rd = request.getRequestDispatcher("selectallemployeeerror.jsp");
		}
		rd.forward(request, response);
	}

}

package edu.servlet.employee;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.dao.EmployeeDao;
import edu.dto.EmployeeDto;
import edu.factory.DaoFactory;

public class InsertEmployeeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		EmployeeDto employeeDto = null;
		RequestDispatcher rd = null;
		try {
			employeeDto = new EmployeeDto();
			employeeDto.setEname(request.getParameter("ename"));
			employeeDto.setSalary(Double.parseDouble(request.getParameter("salary")));
			String stringDate = request.getParameter("hiredate");
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			employeeDto.setHiredate(dateFormat.parse(stringDate));
			EmployeeDao employeeDao = DaoFactory.getEmployeeDao();
			if (employeeDao.insertEmployee(employeeDto)) {
				request.setAttribute("employee", employeeDto);
				rd = request.getRequestDispatcher("insertemployeesucces.jsp");
			} else {
				rd = request.getRequestDispatcher("insertemployeefailure.jsp");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("InsertEmployeeServlet :doGet : " + e);
			rd = request.getRequestDispatcher("insertemployeeerror.jsp");
		}
		rd.forward(request, response);
	}

}

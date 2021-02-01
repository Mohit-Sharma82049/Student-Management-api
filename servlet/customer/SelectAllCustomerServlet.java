package edu.servlet.customer;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.dao.CustomerDao;
import edu.dto.CustomerDto;
import edu.factory.DaoFactory;

public class SelectAllCustomerServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = null;
		ArrayList<CustomerDto> list = null;
		try {
			CustomerDao customerDao = DaoFactory.getCustomerDao();
			list = customerDao.selectAllCustomer();
			if (list.size() > 0) {
				request.setAttribute("list", list);
				rd = request.getRequestDispatcher("selectallcustomersuccess.jsp");
			} else
				rd = request.getRequestDispatcher("selectallcustomerfailure.jsp");
		} catch (Exception e) {
			// TODO: handle exception
			rd = request.getRequestDispatcher("selectallcustomererror.jsp");
		}
		rd.forward(request, response);
	}

}

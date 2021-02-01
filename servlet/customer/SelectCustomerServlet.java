package edu.servlet.customer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.dao.CustomerDao;
import edu.dto.CustomerDto;
import edu.factory.DaoFactory;

public class SelectCustomerServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("accno");
		CustomerDto customerDto = null;
		RequestDispatcher rd = null;
		try {
			Integer eid = Integer.parseInt(id);
			CustomerDao customerDao = DaoFactory.getCustomerDao();
			customerDto = customerDao.selectCustomer(eid);
			if (customerDto != null) {
				request.setAttribute("customer", customerDto);
				rd = request.getRequestDispatcher("selectcustomersuccess.jsp");
			} else
				rd = request.getRequestDispatcher("selectcustomerfailure.jsp");
		} catch (Exception e) {
			// TODO: handle exception
			rd = request.getRequestDispatcher("selectcustomererror.jsp");
		}
		rd.forward(request, response);
	}

}

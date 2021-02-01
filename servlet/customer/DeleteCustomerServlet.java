package edu.servlet.customer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.dao.CustomerDao;
import edu.factory.DaoFactory;

public class DeleteCustomerServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("accno");
		CustomerDao customerDao = null;
		RequestDispatcher rd = null;
		try {
			Integer accno = Integer.parseInt(id);
			customerDao = DaoFactory.getCustomerDao();

			if (customerDao.deleteCustomer(accno)) {
				rd = request.getRequestDispatcher("deletecustomersuccess.jsp");
			} else
				rd = request.getRequestDispatcher("deletecustomerfailure.jsp");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Delete customre Servlet!" + e);
			rd = request.getRequestDispatcher("deletecustomererror.jsp");
		}
		rd.forward(request, response);
	}

}

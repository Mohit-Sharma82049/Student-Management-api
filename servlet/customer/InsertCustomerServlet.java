package edu.servlet.customer;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.dao.CustomerDao;
import edu.dto.CustomerDto;
import edu.factory.DaoFactory;

public class InsertCustomerServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		CustomerDto customerDto = null;
		RequestDispatcher rd = null;
		try {
			customerDto = new CustomerDto();
			customerDto.setCname(request.getParameter("cname"));
			customerDto.setBalance(Double.parseDouble(request.getParameter("balance")));
			String stringDate = request.getParameter("dob");
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			customerDto.setDob(dateFormat.parse(stringDate));
			customerDto.setAddress(request.getParameter("address"));
			CustomerDao customerDao = DaoFactory.getCustomerDao();
			if (customerDao.insertCustomer(customerDto)) {
				request.setAttribute("customer", customerDto);
				rd = request.getRequestDispatcher("insertcustomersuccess.jsp");
			} else {
				rd = request.getRequestDispatcher("insertcustomerfaiure.jsp");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("CustomerInsertServlet doGet : " + e);
			rd = request.getRequestDispatcher("insertcustomererror.jsp");
		}
		rd.forward(request, response);
	}

}

package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import databean.CustomerBean;
import model.CustomerDAO;
import model.Model;

public class CustomerHomeAction extends Action {

	private CustomerDAO customerDAO;

	public CustomerHomeAction(Model model) {
		// TODO Auto-generated constructor stub

		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "customerHome.do";
	}

	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		try {
			if (session.getAttribute("user") != null && session.getAttribute("user") instanceof CustomerBean) {

				CustomerBean customer = (CustomerBean) request	.getSession(false).getAttribute("user");
				
				customer = customerDAO.read(customer.getUserName());

				return "customerHome.jsp";	

			} else {
				// logout and re-login
				if (session.getAttribute("user") != null)
					session.removeAttribute("user");

				return "login.do";
			}

		} catch (Exception e) {
			errors.add(e.getMessage());
			return "login.do";
		}

	}
}

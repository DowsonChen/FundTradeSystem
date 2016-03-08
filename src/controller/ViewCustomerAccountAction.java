package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import databean.EmployeeBean;
import model.CustomerDAO;
import model.Model;

public class ViewCustomerAccountAction extends Action{

	private CustomerDAO customerDAO;

	public ViewCustomerAccountAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "viewCustomerAccount.do";
	}

	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			// If user is already logged in, redirect to todolist.do
			if (session.getAttribute("user") != null
					&& session.getAttribute("user") instanceof EmployeeBean) {
				
				
				return "viewCustomerAccount.jsp";
			} else {
				// logout and re-login
				if (session.getAttribute("user") != null)
					session.removeAttribute("user");
				return "login.do";
			}
		} catch (Exception e) {
			errors.add(e.getMessage());
			return "viewCustomerAccount.jsp";
		}
	}
}

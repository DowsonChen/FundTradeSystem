package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import databean.CustomerBean;
import databean.EmployeeBean;
import model.CustomerDAO;
import model.EmployeeDAO;
import model.Model;

public class LogoutAction extends Action{
	private EmployeeDAO employeeDAO;
	private CustomerDAO customerDAO;
	private Model model;
	
	public LogoutAction(Model model) {
		this.model = model;
		employeeDAO = model.getEmployeeDAO();
		customerDAO = model.getCustomerDAO();
	}
	
	public String getName() {
		return "logout.do";
	}
	
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		if (session.getAttribute("user") instanceof EmployeeBean) {
			EmployeeBean tmp = (EmployeeBean)session.getAttribute("user");
			tmp.setCookie(null);
			try {
				employeeDAO.update(tmp);
			} catch (RollbackException e) {
				e.printStackTrace();
				return "failure-employee.jsp";
			}
			session.setAttribute("user", null);
			return "index.jsp";
		}
		else if (session.getAttribute("user") instanceof CustomerBean) {
			CustomerBean tmp = (CustomerBean)session.getAttribute("user");
			tmp.setCookie(null);
			try {
				customerDAO.update(tmp);
			} catch (RollbackException e) {
				e.printStackTrace();
				return "failure-customer.jsp";
			}
			session.setAttribute("user", null);
			return "index.jsp";
		}
		
		session.setAttribute("user", null);
	
		return "index.jsp";
	}
}
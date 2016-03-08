package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import databean.CustomerBean;
import databean.EmployeeBean;
import formbean.LogUserOutForm;
import model.CustomerDAO;
import model.EmployeeDAO;
import model.Model;


public class LogUserOutAction extends Action{
	private EmployeeDAO employeeDAO;
	private CustomerDAO customerDAO;
	private Model model;
	
	public LogUserOutAction(Model model) {
		this.model = model;
		employeeDAO = model.getEmployeeDAO();
		customerDAO = model.getCustomerDAO();
	}

	@Override
	public String getName() {
		return "logUserOut.do";
	}

	@Override
	public String perform(HttpServletRequest request) {

		HttpSession session = request.getSession();
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
			LogUserOutForm form = new LogUserOutForm(request);
			request.setAttribute("form", form);
			
			if (!form.isPresent()) {
				return "logUserOut.jsp";
			}
			
			errors.addAll(form.getValidationErrors());
			
			if (errors.size() != 0) {
				return "logUserOut.jsp";
			}
			
			if (session.getAttribute("user") instanceof EmployeeBean) {
				EmployeeBean tmp = (EmployeeBean)session.getAttribute("user");
				EmployeeBean tmp1;
				try {
					tmp1 = employeeDAO.read(tmp.getUserName());
					
				} catch (RollbackException e) {
					return "logUserOut.jsp";
				}
				if(!tmp1.getPassword().equals(form.getPasswd())){
					errors.add("Incorrect Password!");
					return "logUserOut.jsp";
				}
				tmp1.setCookie(null);
				try {
					employeeDAO.update(tmp1);
				} catch (RollbackException e) {
					e.printStackTrace();
					return "failure-employee.jsp";
				}
				session.setAttribute("user", null);
				System.out.println("Success");
				return "index.jsp";
			}
			else if (session.getAttribute("user") instanceof CustomerBean) {
				CustomerBean tmp = (CustomerBean)session.getAttribute("user");
				CustomerBean tmp1;
				tmp1 = customerDAO.read(tmp.getUserName());
				if(!tmp1.getPassword().equals(form.getPasswd())){
					errors.add("Incorrect Password!");
					return "logUserOut.jsp";
				}
				tmp1.setCookie(null);
				try {
					customerDAO.update(tmp1);
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

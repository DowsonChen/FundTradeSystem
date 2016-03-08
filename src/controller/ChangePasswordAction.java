package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import databean.EmployeeBean;
import formbean.ChangePasswordForm;
import model.CustomerDAO;
import model.EmployeeDAO;
import model.Model;

public class ChangePasswordAction extends Action{
	private FormBeanFactory<ChangePasswordForm> formBeanFactory = FormBeanFactory
			.getInstance(ChangePasswordForm.class);
	private CustomerDAO customerDAO;
	private EmployeeDAO employeeDAO;

	public ChangePasswordAction(Model model) {
		customerDAO = model.getCustomerDAO();
		employeeDAO = model.getEmployeeDAO();
	}

	public String getName() {
		return "changePassword.do";
	}
	
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		try {
			ChangePasswordForm changePasswordForm = formBeanFactory.create(request);
			request.setAttribute("form", changePasswordForm);
			
			if (request.getSession().getAttribute("user") instanceof CustomerBean) {
				CustomerBean user = (CustomerBean) request.getSession().getAttribute("user");

				if (!changePasswordForm.isPresent()) {
					return "customerChangePassword.jsp";
				}

				errors.addAll(changePasswordForm.getValidationErrors());
				
				if (errors.size() > 0) {
					return "customerChangePassword.jsp";
				}
				
				if (!changePasswordForm.getCurrentPassword().equals(customerDAO.read(user.getUserName()).getPassword())) {
					errors.add("Original password entered is incorrect.");
				}
				if (errors.size() > 0) {
					return "customerChangePassword.jsp";
				}

				/*user.setPassword(changePasswordForm.getNewPassword());
				customerDAO.update(user);*/
				customerDAO.setPassword(user.getUserName(),changePasswordForm.getNewPassword());
				
				request.setAttribute("msg", "Password is updated successfully.");

				return "success-customer.jsp";
			} else if (request.getSession().getAttribute("user") instanceof EmployeeBean) {
				EmployeeBean user = (EmployeeBean) request.getSession().getAttribute("user");
				
				if (!changePasswordForm.isPresent()) {
					return "employeeChangePassword.jsp";
				}

				errors.addAll(changePasswordForm.getValidationErrors());
				
				if (errors.size() > 0) {
					return "employeeChangePassword.jsp";
				}
				
				if (!changePasswordForm.getCurrentPassword().equals(employeeDAO.read(user.getUserName()).getPassword())) {
					errors.add("Original password entered is incorrect.");
				}
				
				if (errors.size() > 0) {
					return "employeeChangePassword.jsp";
				}

				/*user.setPassword(changePasswordForm.getNewPassword());
				employeeDAO.update(user);*/
				employeeDAO.setPassword(user.getUserName(), changePasswordForm.getNewPassword());
				
				request.setAttribute("msg", "Password is updated successfully.");
				
				return "success-employee.jsp";
			} else {
				return "index.html";
			}
		} catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
	}
}

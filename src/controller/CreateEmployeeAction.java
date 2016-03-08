package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.EmployeeBean;
import formbean.CreateEmployeeForm;
import model.EmployeeDAO;
import model.Model;

public class CreateEmployeeAction extends Action{
	private FormBeanFactory<CreateEmployeeForm> formBeanFactory = FormBeanFactory
			.getInstance(CreateEmployeeForm.class);

	private EmployeeDAO employeeDAO;

	public CreateEmployeeAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
	}

	public String getName() {
		return "createEmployee.do";
	}

	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		try {
			if (session.getAttribute("user") != null && session.getAttribute("user") instanceof EmployeeBean) {
				CreateEmployeeForm form = formBeanFactory.create(request);
				request.setAttribute("form", form);
				
				if (!form.isPresent()) {
					return "createEmployee.jsp";
				}

				errors.addAll(form.getValidationErrors());
				if (errors.size() > 0) {
					return "createEmployee.jsp";
				}

				if (employeeDAO.read(form.getUserName()) != null) {
					errors.add("This username already exists. Please select a new username.");
					return "createEmployee.jsp";
				}	
				Transaction.begin();
				EmployeeBean newUser = new EmployeeBean();
				newUser.setUserName(form.getUserName());
				newUser.setFirstName(form.getFirstName());
				newUser.setLastName(form.getLastName());
				newUser.setPassword(form.getPassword());
				employeeDAO.create(newUser);
				Transaction.commit();
				request.setAttribute("message", "Employee account for " +form.getUserName()+ " was created successfully.");
				request.removeAttribute("form");

				return "success-employee.jsp";
			} else {
				return "createEmployee.jsp";
			}
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "createEmployee.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "createEmployee.jsp";
		}
	}
}
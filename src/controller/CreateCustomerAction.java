package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import databean.EmployeeBean;
import formbean.CreateCustomerForm;
import model.CustomerDAO;
import model.Model;

public class CreateCustomerAction extends Action {

	private FormBeanFactory<CreateCustomerForm> formBeanFactory = FormBeanFactory.getInstance(CreateCustomerForm.class);

	private CustomerDAO customerDAO;

	public CreateCustomerAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "createCustomer.do";
	}

	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			if (session.getAttribute("user") != null && session.getAttribute("user") instanceof EmployeeBean) {
				CreateCustomerForm form = formBeanFactory.create(request);
				request.setAttribute("form", form);

				if (!form.isPresent()) {
					return "createCustomer.jsp";
				}

				errors.addAll(form.getValidationErrors());
				if (errors.size() > 0) {
					return "createCustomer.jsp";
				}

				if (customerDAO.read(form.getUserName()) != null) {
					errors.add("This username already exists. Please select a new username.");
					return "createCustomer.jsp";
				}
//				double cash = Double.parseDouble(form.getCash()) * 100.0;
				Transaction.begin();
				CustomerBean newUser = new CustomerBean();
				newUser.setUserName(form.getUserName());
				newUser.setLastName(form.getLastName());
				newUser.setFirstName(form.getFirstName());
				newUser.setPassword(form.getPassword());
				newUser.setAddress1(form.getAddress1());
				newUser.setAddress2(form.getAddress2());
				newUser.setCity(form.getCity());
				newUser.setState(form.getState());
				newUser.setZipcode(form.getZip());
				newUser.setCash(0);
//				newUser.setCash((long)(cash));

				customerDAO.create(newUser);
				Transaction.commit();

				request.removeAttribute("form");
				request.setAttribute("message", "Customer account is created successfully.");

				return "success-employee.jsp";
			} else {
				return "createCustomer.jsp";
			}
		} catch (NumberFormatException e) {
			errors.add("Either the number is too long or it is not a number. Please enter a valid number.");
			return "createCustomer.jsp";
		}catch (RollbackException e) {
			errors.add(e.getMessage());
			return "createCustomer.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "createCustomer.jsp";
		}
	}
}

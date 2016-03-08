package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.EmployeeBean;
import formbean.ResetCustPassForm;
import model.CustomerDAO;
import model.Model;


/*
 * Logs out by setting the "user" session attribute to null.
 * (Actions don't be much simpler than this.)
 */
public class ResetCustPassAction extends Action {
	private FormBeanFactory<ResetCustPassForm> formBeanFactory = FormBeanFactory
			.getInstance(ResetCustPassForm.class);
	private CustomerDAO customerDAO;
	public ResetCustPassAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "reset.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			request.setAttribute("customerList", customerDAO.match());

			if (request.getSession().getAttribute("user") instanceof EmployeeBean) {

				ResetCustPassForm form = formBeanFactory.create(request);
//				System.out.println("======================");
//				System.out.println(form.getCustomer1());
//				System.out.println(form.getCustomer2());
//				System.out.println(form.getCustomer());
//				System.out.println(form.getNewPass());
//				System.out.println(form.getConfPass());
//				System.out.println(form.getAction());
//				request.setAttribute("form", form);
//
//				request.setAttribute("customer", request.getParameter("customer"));

				if (!form.isActionPresent()) {
					return "resetCustPass.jsp";
				}

				errors.addAll(form.getValidationErrors());

				if (errors.size() > 0) {
					return "resetCustPass.jsp";
				}

				//CustomerBean user = customerDAO.read(form.getCustomer());

				/*if (user == null) {
					errors.add("User Name does not exist");
					return "resetCustPass.jsp";
				}
				
				user.setPassword(form.getNewPass());
				customerDAO.update(user);*/
				customerDAO.setPassword(form.getCustomer(),form.getNewPass());
				return "success-employee.jsp";


				
			}
			else {
				if (request.getSession().getAttribute("user") != null)
					request.getSession().removeAttribute("user");

				return "login.do";
			}

		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "resetCustPass.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "resetCustPass.jsp";
		}

	}
}


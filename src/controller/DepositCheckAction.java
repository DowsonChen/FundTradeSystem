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
import databean.TransactionBean;
import formbean.DepositCheckForm;
import model.CustomerDAO;
import model.Model;
import model.TransactionDAO;

public class DepositCheckAction extends Action {

	private FormBeanFactory<DepositCheckForm> formBeanFactory = FormBeanFactory.getInstance(DepositCheckForm.class);
	private TransactionDAO transactionDAO;
	private CustomerDAO customerDAO;

	public DepositCheckAction(Model model) {
		transactionDAO = model.getTransactionDAO();
		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "depositCheck.do";
	}
	
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		HttpSession session = request.getSession();
		
		try {
			DepositCheckForm depisitCheckForm = formBeanFactory.create(request);
			request.setAttribute("form",depisitCheckForm);
			if (session.getAttribute("user") == null) {
				return "login.do";
			}
			
			if (!depisitCheckForm.isPresent()) {
				return "depositCheck.jsp";
			}
			errors.addAll(depisitCheckForm.getValidationErrors());
			if (errors.size() != 0) {
				return "depositCheck.jsp";
			}
			
			String s =  depisitCheckForm.getDepositAmount();
			CustomerBean customerBean = customerDAO.read(depisitCheckForm.getUserName());
			double d = Double.parseDouble(s);
			d = d * 100.0;
			long l = (long) d;
			
			if (customerBean == null) {
				errors.add("No such user! ");
				return "depositCheck.jsp";
			}
			if ((d - l) > 0) {
				errors.add("We only allow at most two decimal places");
				return "depositCheck.jsp"; 
			}
			Transaction.begin();
			TransactionBean tBean = new TransactionBean();
			tBean.setCustomerId(customerBean.getCustomerId());
			tBean.setTransactionType("1");
			tBean.setAmount(l);
			tBean.setUserName(customerBean.getUserName());
			transactionDAO.create(tBean);
			Transaction.commit();
			request.removeAttribute("form");
			return "success-employee.jsp";
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			errors.add(e.getMessage());
			return "depositCheck.jsp";
		} catch (FormBeanException e) {
			// TODO Auto-generated catch block
			errors.add(e.getMessage());
			return "depositCheck.jsp";
		}
	}

}

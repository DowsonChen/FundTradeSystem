package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class DepositCheckForm extends FormBean {
	private String userName;
	private String depositAmount;
	private String action;

	public String getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(String depositAmount) {
		this.depositAmount = sanitize(depositAmount);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = sanitize(userName);
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public boolean isPresent() {
		return action != null;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		try {
			Double.parseDouble(depositAmount);
		} catch (NumberFormatException e1) {
			errors.add("Amount entered is not a valid Dollar amount. ");

			return errors;
		}
		
		if (Double.parseDouble(depositAmount) > 1000000.0) {
			errors.add("The amount should be less than 1,000,000. ");
		}
		

		if (Double.parseDouble(depositAmount) > 1000000) {
			errors.add("Please enter an amount less than $ 1,000,000. ");
			return errors;
		}
		
		if (Double.parseDouble(depositAmount) <= 0) {
			errors.add("The amount of request should be positive. ");
		}

		if (errors.size() > 0)
			return errors;

		return errors;
	}
	
	private String sanitize(String s) {
		return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
	}

}

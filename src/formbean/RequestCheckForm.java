package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class RequestCheckForm extends FormBean {
	private String requestAmount;
	private String action;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public boolean isPresent() {
		return action != null;
	}
	

	public String getRequestAmount() {
		return requestAmount;
	}

	public void setRequestAmount(String requestAmount) {
		this.requestAmount = sanitize(requestAmount);
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		try {
			Double.parseDouble(requestAmount);
		} catch (NumberFormatException e1) {
			errors.add("Amount entered is not a valid Dollar amount. ");
			return errors;
		}
		
		if (Double.parseDouble(requestAmount) > 1000000.0) {
			errors.add("The amount should be less than 1,000,000. ");
		}

		if (Double.parseDouble(requestAmount) > 1000000) {
			errors.add("Please enter an amount less than $ 1,000,000. ");
		}
		
		if (Double.parseDouble(requestAmount) <= 0) {
			errors.add("The amount of request should be a positive. ");
		}

		if (errors.size() > 0)
			return errors;

		return errors;
	}
	
	private String sanitize(String s) {
		return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
	}

}

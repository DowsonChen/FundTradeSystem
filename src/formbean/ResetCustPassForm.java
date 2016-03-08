
package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class ResetCustPassForm extends FormBean {

	private	String customer1;
	private	String customer2;
	private String newPass;
	private String confPass;
	private String action;

	public String getCustomer1() {
		return customer1;
	}

	public void setCustomer1(String customer1) {
		this.customer1 = sanitize(customer1);
	}

	public String getCustomer2() {
		return customer2;
	}

	public void setCustomer2(String customer2) {
		this.customer2 = sanitize(customer2);
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = sanitize(newPass);
	}

	public String getConfPass() {
		return confPass;
	}

	public void setConfPass(String confPass) {
		this.confPass = sanitize(confPass);
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public String getCustomer() {
		if (customer1 != null && customer2.length() > 0)
			return customer2;
		else
			return customer1;
	}

	public boolean isActionPresent() { return action != null; }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if ((customer1 == null || customer1.length() == 0)
				&& (customer2 == null || customer2.trim().length() == 0))
			errors.add("Username is required");
		
		if ((customer1 !=  null && customer2 != null && customer1.trim().length() != 0 && customer2.trim().length() != 0  && !customer1.trim().equals(customer2.trim()))) {
			errors.add("Usernames inconcistency");
		}
		if (newPass == null || newPass.trim().length() == 0) {
			errors.add("New password is required");
		}

		if (confPass == null || confPass.trim().length() == 0) {
			errors.add("Confirm Password is required");
		}
		if (action == null || action.length() == 0) {
			errors.add("button is required");
		}

		if (errors.size() > 0) {
			return errors;
		}

		if (!newPass.equals(confPass)) {
			errors.add("Passwords do not match");
		}

		if (!action.equals("reset")){
			errors.add("Invalid Action");
		}

		if (customer1.matches(".*[<>\"].*") && customer2.matches(".*[<>\"].*"))
			errors.add("User Name may not contain angle brackets or quotes");

		return errors;
	}
	

    private String sanitize(String s) {
            return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }
}



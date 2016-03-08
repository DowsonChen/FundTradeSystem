package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class ViewCustomerForm extends FormBean {
	private String userName1;
	private String userName2;
	private String action;

	public void setUserName1(String s) {
		userName1 = s.trim();
	}

	public void setUserName2(String s) {
		String source = s.trim();
		userName2 = source.toLowerCase();
	}

	public void setAction(String s) {
		action = s;
	}

	public String getUserName1() {
		return userName1;
	}

	public String getUserName2() {
		return userName2;
	}

	public String getAction() {
		return action;
	}

	public String getUserName() {
		if (userName1 != null && userName1.length() > 0)
			return userName1;
		else
			return userName2;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if ((userName1 == null || userName1.length() == 0)
				&& (userName2 == null || userName2.length() == 0))
			errors.add("Username is required");

		if (action == null)
			errors.add("Button is required");

		if (errors.size() > 0)
			return errors;
		
		if ((userName1 !=  null && userName2 != null && userName1.trim().length() != 0 && userName2.trim().length() != 0  && !userName1.trim().equals(userName2.trim()))) {
			errors.add("Usernames inconcistency");
		}

		if (!action.equals("select"))
			errors.add("Invalid button");
		if (userName1.matches(".*[<>\"].*") && userName2.matches(".*[<>\"].*"))
			errors.add("User Name may not contain angle brackets or quotes");

		return errors;
	}

}

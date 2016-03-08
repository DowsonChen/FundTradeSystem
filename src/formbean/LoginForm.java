package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class LoginForm extends FormBean {
	public String userNm;
	private String password;
	private String type;

	public String getUserName() {
		return userNm;
	}

	public void setUserName(String userName) {
		//System.out.println("uname: " + userName);
		this.userNm = sanitize(userName);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = sanitize(password);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isEmployee() {
		return type != null && type.equals("employee");
	}
	
	public boolean isCustomer() {
		return type != null && type.equals("customer");
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		
		if (password == null || password.trim().length() == 0){
			errors.add("Password is required");
		}
		//System.out.println("name in errors:" + userNm);
		if (userNm == null || userNm.trim().length() == 0){
			errors.add("Username is required");
		}
		if (type == null){
			errors.add("Type is required");
		}

		if (errors.size() > 0)
			return errors;
		
		return errors;
	}
	
	private String sanitize(String s) {
		return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
	}
}

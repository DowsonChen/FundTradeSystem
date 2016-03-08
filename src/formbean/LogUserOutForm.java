package formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class LogUserOutForm {
	private String action;
	private String passwd;
	
	public LogUserOutForm(HttpServletRequest request){
		action = request.getParameter("action");
		passwd = request.getParameter("passwd");
	}
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public boolean isPresent() {
		return action != null;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (passwd == null || passwd.trim().length() == 0){
			errors.add("Password is required");
		}
		if (!action.equals(action)){
			errors.add("No action detected!");
		}
		
		return errors;
		
	}
	
}

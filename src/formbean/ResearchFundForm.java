package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class ResearchFundForm extends FormBean {
	private String fundName;
	
	private String action;	
	
	public String getFundName() {
		return fundName;
	}	

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if (action == null) {
			return errors;
		} else if (action.equals("Research")) {
			if (fundName == null || fundName.length() == 0 ) {
				errors.add("No Fund Selected.");
				return errors;
			}
			
		} else {
			errors.add("Invalid Action: "+ action);

		}
		
		return errors;
	}

	public String getAction() {
		
		return action;
	}

	public void setAction(String button) {
		this.action = button;
		
	}
	
	
}


package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class SellFundForm extends FormBean{
	private String 	name;
	private String 	shares;
	
	public String getName() {
		return name;
	}

	public String getShares() {
		return shares;
	}

	public void setName(String name) {
		this.name = sanitize(name);
	}

	public void setShares(String shares) {
		this.shares = sanitize(shares);
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (name == null || name.trim().length() == 0)
			errors.add("Fund name is required. ");
		if (shares == null || !shares.matches(".*\\d.*"))
			errors.add("Shares should be numeric. ");
		if (shares != null) {
			if (shares.trim().length() == 0) {
				errors.add("You should put the number of shares. ");
			} else if (Double.parseDouble(shares) < 0){
				errors.add("Shares should not be negative. ");
			}
		}
		
		if (errors.size() > 0)
			return errors;
		
		return errors;
	}
	
	private String sanitize(String s) {
		return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
	}
}

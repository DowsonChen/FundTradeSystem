package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class BuyFundForm extends FormBean{
	private String 	name;
	private String 	amount;
	
	public String getName() {
		return name;
	}

	public String getAmount() {
		return amount;
	}

	public void setName(String name) {
		this.name = sanitize(name);
//		this.name = name;
	}

	public void setAmount(String amount) {
		this.amount = sanitize(amount);
//		this.amount = amount;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (name == null || name.trim().length() == 0)
			errors.add("Fund name is required. ");
		if (amount == null || !amount.matches(".*\\d.*")) 
			errors.add("Amount should be numeric. ");
		if (amount != null) {
			if (amount.trim().length() == 0) {
				errors.add("You should put the amount. ");
			} else if (Double.parseDouble(amount) < 0){
				errors.add("Amount should not be negative. ");
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

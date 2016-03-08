package formbean;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CreateFundForm extends FormBean{
	private String 	symbol;
	private String 	name;
	
	public String getSymbol() {
		return symbol;
	}
	public String getName() {
		return name;
	}
	public void setSymbol(String symbol) {
		this.symbol = sanitize(symbol);
	}
	public void setName(String name) {
		this.name = sanitize(name);
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (name == null || name.trim().length() == 0)
			errors.add("Fund name is required");
		if (name.matches(".*\\d.*")) 
			errors.add("Fund name should not contain number");
		if (symbol == null || symbol.trim().length() < 1
				|| symbol.trim().length() >5)
			errors.add("The length of symbol should be between 1~5");

		if (errors.size() > 0)
			return errors;
		
		return errors;
	}
	
	private String sanitize(String s) {
		return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
	}
}

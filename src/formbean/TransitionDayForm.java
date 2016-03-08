
package formbean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

//import org.mybeans.form.FormBean;

public class TransitionDayForm /* extends FormBean */ {
	private String date;
	private String action;

	public TransitionDayForm(HttpServletRequest request) {
		action = request.getParameter("action");
		date = request.getParameter("date");
	}

	public String getDate() {
		return date;
	}

	public String getAction() {
		return action;
	}

	public boolean isPresent() {
		return action != null;
	}

	public List<String> getValidationErrors(HashMap<String, String> map) {
		List<String> errors = new ArrayList<String>();

		if (date == null || date.length() == 0)
			errors.add("Date required. ");
		if (action == null)
			errors.add("No Action detected. ");

		if (errors.size() > 0)
			return errors;
		if (!action.equals("create"))
			errors.add("Invalid Action. ");

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			dateFormat.setLenient(false);
			dateFormat.parse(date);
		} catch (Exception e) {
			errors.add("Invalid date. ");
		}

		for (String price : map.values()) {

			try {
				double d = Double.parseDouble(price);
				// 2 digit allowed!
				int lastDotIndex = price.lastIndexOf(".");
				if (lastDotIndex != -1 && price.substring(lastDotIndex + 1).length() > 2
						&& Integer.parseInt(price.substring(lastDotIndex + 1)) != 0) {
					errors.add("Price format error! Dollar amount can be upto 2 decimal places only.");
				} else if (d < 0.01 || d > 1000) {
					errors.add("Price of shares must be between One cent ($0.01) and One thousand ($1,000.00). ");
				}
			} catch (Exception e) {
				errors.add("Price format error. ");
			}
		}

		return errors;
	}
}